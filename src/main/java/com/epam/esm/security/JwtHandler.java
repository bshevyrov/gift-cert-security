package com.epam.esm.security;

import com.epam.esm.exception.auth.TokenExpireException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.security.Key;
import java.util.Date;

public class JwtHandler {
    @Autowired
    MessageSource messageSource;
    private final String secret;

    public JwtHandler(String secret) {
        this.secret = secret;
    }

    public VerificationResult check(String accessToken) {
        return verify(accessToken);
        //onerror resume un authorize
    }

    private VerificationResult verify(String token) {
        Claims claims = getClaimsFromToken(token);
        final Date expirationDate = claims.getExpiration();

        if (expirationDate.before(new Date())) {
            throw new TokenExpireException(messageSource.getMessage("token.expire.exception",
                    new Object[]{},
                    LocaleContextHolder.getLocale()));
        }
        return new VerificationResult(claims, token);
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static class VerificationResult {
        public Claims claims;
        public String token;

        public VerificationResult(Claims claims, String token) {
            this.claims = claims;
            this.token = token;
        }
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
