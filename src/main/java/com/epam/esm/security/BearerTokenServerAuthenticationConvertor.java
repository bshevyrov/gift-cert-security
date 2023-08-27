//package com.epam.esm.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
//import org.springframework.web.server.ServerWebExchange;
//
//import java.util.function.Function;
//
//@RequiredArgsConstructor
//public class BearerTokenServerAuthenticationConvertor implements ServerAuthenticationConverter {
////    100
//    private final JwtHandler jwtHandler;
//    public static final String BEARER_PREFIX = "Bearer ";
//    public static final Function<String,Mono<String>> getBearerValue = authValue -> Mono.justOrEmpty;
//
//    @Override
//    public reactor.core.publisher.Mono<Authentication> convert(ServerWebExchange exchange) {
//        return null;
//    }
//}
