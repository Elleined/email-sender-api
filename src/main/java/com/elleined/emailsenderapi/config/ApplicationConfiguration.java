package com.elleined.emailsenderapi.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }
}
