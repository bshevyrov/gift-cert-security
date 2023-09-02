package com.epam.esm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableJpaAuditing
@EnableWebSecurity
public class GiftCertAdvancedApplication {
    public static void main(String[] args) {
        SpringApplication.run(GiftCertAdvancedApplication.class, args);
    }
}
