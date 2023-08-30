package com.epam.esm.security.jwt;

import com.epam.esm.exception.auth.InvalidTokenException;
import com.epam.esm.persistence.entity.RoleEntity;
import com.epam.esm.service.CustomerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generates token.
 */
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final UserDetailsService userDetailsService;
    private final CustomerService customerService;
    private final MessageSource messageSource;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expirationInSeconds;

    /**
     * Creates token.
     *
     * @param username parameter for token.
     * @return String representation of token
     */
    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", getRoleNames(customerService.findByUsername(username).getRoleEntities()));
        Date now = new Date();
        long expirationTimeInMillis = expirationInSeconds * 1000L;
        Date expirationDate = new Date(new Date().getTime() + expirationTimeInMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .setIssuedAt(now)
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Creates authentication from token.
     *
     * @param token String representation of token.
     * @return {@link Authentication}
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Gets username from token.
     *
     * @param token String representation of token.
     * @return username.
     */
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Validates token. Can throw {@link InvalidTokenException}
     *
     * @param token String representation of token.
     * @return true if token valid and not expired.
     */
    public boolean validateToken(String token) {
        try {
            Date expiration = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            if (expiration.before(new Date())) {
                return false;
            }
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException(messageSource.getMessage("invalid.token.exception",
                    new Object[]{},
                    LocaleContextHolder.getLocale()));
        }
        return true;
    }

    /**
     * Extracts token from http request header.
     *
     * @param req {@link HttpServletRequest}
     * @return String representation of token.
     */
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (ObjectUtils.isNotEmpty(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(("Bearer ").length());
        }
        return null;
    }

    /**
     * Creates list of role names from list of {@link RoleEntity}
     *
     * @param userRoles list of role entity
     * @return list of strings.
     */
    private List<String> getRoleNames(List<RoleEntity> userRoles) {
        return userRoles.stream()
                .map(RoleEntity::getName)
                .collect(Collectors.toList());
    }

    /**
     * Creates Key object.
     *
     * @return !{@link Key}
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
