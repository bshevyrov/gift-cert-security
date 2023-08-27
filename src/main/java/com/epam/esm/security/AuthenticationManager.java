//package com.epam.esm.security;
//
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class AuthenticationManager implements org.springframework.security.authentication.AuthenticationManager {
//    private final UserRepository userRepository;
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//      CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
//        return userRepository.findById(principal.getId())
//              .filter(UserEntoty::isEanbled)
//                .switchIfEmpty( new AuthenticationException("User Disabled"))
//                .map(user-> authentication);
//
//    }
//}
