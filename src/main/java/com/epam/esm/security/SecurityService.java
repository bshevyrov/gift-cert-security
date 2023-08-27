//package com.epam.esm.security;
//
//import com.epam.esm.exception.auth.InvalidPasswordException;
//import com.epam.esm.exception.auth.InvalidUsernameException;
//import com.epam.esm.exception.customer.CustomerDisabledException;
//import com.epam.esm.exception.customer.CustomerNotFoundException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.MessageSource;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@Component
//@RequiredArgsConstructor
//public class SecurityService {
//    private final PasswordEncoder passwordEncoder;
//    private final UserRepository userRepository;
//    private final MessageSource messageSource;
//    @Value("${jwt.secret}")
//    private String secret;
//    @Value("${jwt.expiration}")
//    private Integer expirationInSeconds;
//    @Value("${jwt.issuer}")
//    private String issuer;
//
//
//    private TokenDetails generateToken(UserEntity userEntity) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", userEntity.getRole());
//        claims.put("username", userEntity.getUsaername());
//        return generateToken(claims, userEntity.getId().toString);
//    }
//
//    private TokenDetails generateToken(Map<String, Object> claims, String subject) {
//        long expirationTimeInMillis = expirationInSeconds * 1000L;
//        Date expirationDate = new Date(new Date().getTime() + expirationTimeInMillis);
//        return generateToken(expirationDate, claims, subject);
//    }
//
//
//    private TokenDetails generateToken(Date expirationDate, Map<String, Object> claims, String subject) {
//        Date createdDate = new Date();
//        String token = Jwts.builder()
//                .setClaims(claims)
//                .setIssuer(issuer)
//                .setSubject(subject)
//                .setId(UUID.randomUUID().toString())
//                .setExpiration(expirationDate)
//                .signWith(getSigningKey())
//                .compact();
//
//        return TokenDetails.builder()
//                .token(token)
//                .issuedAt(createdDate)
//                .expiresAt(expirationDate)
//                .build();
//    }
//
//    public TokenDetails authenticate(String username, String password) {
//        User user = userRepository.findByUsername(username).orElseThrow(
//                () -> new InvalidUsernameException(
//                        messageSource.getMessage("invalid.username.exception",
//                                new Object[]{username},
//                                LocaleContextHolder.getLocale())));
//        if (!user.isEnabled()) {
//            throw new CustomerDisabledException(messageSource.getMessage("customer.disabled.exception",
//                    new Object[]{username},
//                    LocaleContextHolder.getLocale()));
//        }
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new InvalidPasswordException(messageSource.getMessage("invalid.password.exception",
//                    new Object[]{},
//                    LocaleContextHolder.getLocale()));
//        }
//        return generateToken(user).toBuilder()
//                .userId(user.getId());
//
//    }
//
//    private Key getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}
