package com.elleined.emailsenderapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.security.SecureRandom;

@EnableAsync
@SpringBootApplication
public class EmailSenderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailSenderApiApplication.class, args);
	}

	@Bean
	public SecureRandom secureRandom() {
		return new SecureRandom();
	}
}
