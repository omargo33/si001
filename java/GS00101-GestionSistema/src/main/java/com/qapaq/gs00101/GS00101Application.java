package com.qapaq.gs00101;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
// import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Objeto de llamado principal del sistema.
 * 
 * @author o.velez@qapaq.io
 * @since 2020-08-02
 * 
 *        Datos de configuracion de la aplicacion que son agregadas por las
 *        seguridades por API.
 * 
 * @see "https://www.digitalocean.com/community/tutorials/spring-boot-redis-cache"
 * @see "https://www.youtube.com/watch?v=19EADd8U0-4&t=4s"
 * 
 * @EnableJpaRepositories(basePackages = {"com.qapaq.gs00100.jpa.queries",
 *                                     "com.qapaq.ar00100.jpa.queries",})
 * 
 * @see seguridad
 */
@ComponentScan(basePackages = {
                "com.qapaq.gs00101.http.request",
                "com.qapaq.gs00101.servicio",
                "com.qapaq.gs00101.validadores",
                "com.qapaq.gs00101.configuracion",
})
@EntityScan(basePackages = {
                "com.qapaq.gs00101.jpa.model",
})

@EnableJpaRepositories(basePackages = {
                "com.qapaq.gs00101.jpa.queries",
})

@SpringBootApplication
// @EnableEurekaClient
@EnableCaching
public class GS00101Application {

        public static void main(String[] args) {
                SpringApplication.run(GS00101Application.class, args);
        }
}