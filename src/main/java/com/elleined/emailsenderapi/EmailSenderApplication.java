package com.elleined.emailsenderapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmailSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailSenderApplication.class, args);
	}
}
