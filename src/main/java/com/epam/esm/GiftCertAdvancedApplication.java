package com.epam.esm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories( "com.epam.esm.repository")
public class GiftCertAdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(GiftCertAdvancedApplication.class, args);
    }
}

