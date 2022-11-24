package com.qapaq.gs00100;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Objeto de llamado principal del sistema.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-08-02
 * 
 * @see seguridad
 */
@ComponentScan(
        {
            "com.qapaq.gs00100.security",
            "com.qapaq.gs00100.http.request",
            "com.qapaq.gs00100.servicio",            
            //"com.qapaq.gs00100.validadores",
        }
)
@EnableJpaRepositories("com.qapaq.gs00100.jpa.queries")
@EntityScan("com.qapaq.gs00100.jpa.model")
@SpringBootApplication
public class GS00100Application {

	public static void main(String[] args) {
		SpringApplication.run(GS00100Application.class, args);
	}
        
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }        
}