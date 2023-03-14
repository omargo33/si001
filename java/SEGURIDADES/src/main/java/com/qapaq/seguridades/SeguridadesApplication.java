package com.qapaq.seguridades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ComponentScan({
		"com.qapaq.catalogos.*",
		/*
		"com.qapaq.catalogos.servicio",
		"com.qapaq.catalogos.jpa.queries",
		*/
		
		"com.qapaq.seguridades.*",
		/*
		"com.qapaq.seguridades.servicio",
		"com.qapaq.seguridades.jpa.queries",
		"com.qapaq.seguridades.http.request",		
		"com.qapaq.seguridades.validadores",
		"com.qapaq.seguridades.configuracion",
		*/
})

@EntityScan({
		"com.qapaq.catalogos.jpa.model",
		"com.qapaq.seguridades.jpa.model",
})


@EnableJpaRepositories({
		"com.qapaq.catalogos.jpa.queries",
		"com.qapaq.seguridades.jpa.queries"
})



@SpringBootApplication
public class SeguridadesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeguridadesApplication.class, args);
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