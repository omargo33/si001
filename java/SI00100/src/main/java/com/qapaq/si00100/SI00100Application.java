package com.qapaq.si00100;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Objeto de llamado principal del sistema.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-02
 */
@ComponentScan(
        {
            "com.qapaq.si00100.http.request",
            "com.qapaq.si00100.servicio",
            "com.qapaq.si00100.validadores",            
        }
)
@EnableJpaRepositories("com.qapaq.si00100.jpa.queries")
@EntityScan("com.qapaq.si00100.jpa.model")
@SpringBootApplication
public class SI00100Application {
	public static void main(String[] args) {
		SpringApplication.run(SI00100Application.class, args);
	}
}
