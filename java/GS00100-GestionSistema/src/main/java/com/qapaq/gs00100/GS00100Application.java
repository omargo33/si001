package com.qapaq.gs00100;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Objeto de llamado principal del sistema.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-08-02
 * 
 *       Datos de configuracion de la aplicacion que son agregadas por las
 *       seguridades por API.
 * 
 * @EnableJpaRepositories(basePackages = {"com.qapaq.gs00100.jpa.queries",
 *                                     "com.qapaq.ar00100.jpa.queries",})
 * 
 * @see seguridad
 */
@ComponentScan(basePackages = {
        "com.qapaq.gs00100.http.request",
        "com.qapaq.gs00100.servicio",
        "com.qapaq.gs00100.validadores",
        "com.qapaq.gs00100.configuracion",
})
@EntityScan(basePackages = {
        "com.qapaq.gs00100.jpa.model",
})

@EnableJpaRepositories(basePackages = {
        "com.qapaq.gs00100.jpa.queries",
})

@SpringBootApplication
@EnableEurekaClient
public class GS00100Application {

    public static void main(String[] args) {
        SpringApplication.run(GS00100Application.class, args);
    }
}