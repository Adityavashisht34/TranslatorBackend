package com.TranslatorBackend.TranslatorBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication

public class TranslatorBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TranslatorBackendApplication.class, args);
	}

}
