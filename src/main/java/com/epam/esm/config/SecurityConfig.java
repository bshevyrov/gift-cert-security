package com.epam.esm.config;

import com.epam.esm.security.FilterChainExceptionHandler;
import com.epam.esm.security.jwt.JwtConfigurer;
import com.epam.esm.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for security.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String USER_ENDPOINT = "/api/v1/user/**";
    private static final String GUEST_ENDPOINT = "/api/v1/**";
    private final JwtTokenProvider jwtTokenProvider;
    private final FilterChainExceptionHandler filterChainExceptionHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .addFilterBefore(filterChainExceptionHandler, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .antMatchers(USER_ENDPOINT).hasRole("USER")
                .antMatchers(GUEST_ENDPOINT).permitAll()
//                .anyRequest()
//                .authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));

        return httpSecurity.build();
    }
}
