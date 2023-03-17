package com.qapaq.lg00100;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ComponentScan({
		"com.qapaq.ca00100.*",
		"com.qapaq.lg00100.*",
})

@EntityScan({
		"com.qapaq.ca00100.jpa.model",
		"com.qapaq.lg00100.jpa.model",
})

@EnableJpaRepositories({
		"com.qapaq.ca00100.jpa.queries",
		"com.qapaq.lg00100.jpa.queries"
})

@SpringBootApplication
public class LG00100Application {

	public static void main(String[] args) {
		SpringApplication.run(LG00100Application.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

