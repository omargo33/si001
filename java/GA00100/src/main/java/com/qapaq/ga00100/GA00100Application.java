package com.qapaq.ga00100;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
	"com.qapaq.ga00100.http.request",
	"com.qapaq.ga00100.servicio",	
	"com.qapaq.ga00100.validadores",
	//"com.qapaq.gs00100.configuracion",
})
@EntityScan(basePackages = {
	"com.qapaq.ga00100.jpa.model",
	//"com.qapaq.ar00100.jpa.model",
})
@SpringBootApplication
public class GA00100Application {

	public static void main(String[] args) {
		SpringApplication.run(GA00100Application.class, args);
	}

}
