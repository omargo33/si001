package com.qapaq.gs00100;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Objeto de llamado principal del sistema.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-08-02
 * 
 * Datos de configuracion de la aplicacion que son agregadas por las seguridades por API.
 * 
 * @EnableJpaRepositories(basePackages = {"com.qapaq.gs00100.jpa.queries", "com.qapaq.ar00100.jpa.queries",})
 * 
 * @see seguridad
 */
@ComponentScan(basePackages = {
        "com.qapaq.gs00100.http.request",
        "com.qapaq.gs00100.servicio",
        "com.qapaq.ar00100.servicio",
        "com.qapaq.gs00100.validadores",
        "com.qapaq.gs00100.configuracion",
})
@EntityScan(basePackages = {
        "com.qapaq.gs00100.jpa.model",
        "com.qapaq.ar00100.jpa.model",
})
@SpringBootApplication
public class GS00100Application {

    public static void main(String[] args) {
        SpringApplication.run(GS00100Application.class, args);
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