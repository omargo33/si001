package com.qapaq.gs00100;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(
        {
            "com.qapaq.gs00100.http.request",
            "com.qapaq.gs00100.servicio"
            /*"com.aplicaciones13.manifiesto.scheduler"*/
        }
)
@EnableJpaRepositories("com.qapaq.gs00100.jpa.queries")
@EntityScan("com.qapaq.gs00100.jpa.model")
@SpringBootApplication
public class GS00100Application {

	public static void main(String[] args) {
		SpringApplication.run(GS00100Application.class, args);
	}

}