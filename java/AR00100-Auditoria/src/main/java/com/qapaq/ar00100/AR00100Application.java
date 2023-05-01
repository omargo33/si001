package com.qapaq.ar00100;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Clase principal de la aplicación.
 * 
 * <p>Esta clase es la encargada de iniciar la aplicación,
 * se encarga de escanear los paquetes de la aplicación
 * para encontrar los componentes que se van a utilizar
 * en la aplicación.
 * 
 * <p>Se encarga de habilitar el cliente de Eureka para
 * que la aplicación pueda registrarse en el servidor
 * de Eureka.
 * 
 * <p>Se encarga de habilitar los repositorios de JPA
 * para que la aplicación pueda acceder a la base de
 * datos.
 * 
 * <p>Se encarga de habilitar los componentes de Spring
 * para que la aplicación pueda utilizarlos.
 * 
 * <p>Se encarga de habilitar los validadores de Spring
 * para que la aplicación pueda utilizarlos.
 * 
 * @author o.velez@qapaq.io
 * @since 2023-04-30
 * 
 */
@ComponentScan(basePackages = {
		"com.qapaq.ar00100.http.request",
		"com.qapaq.ar00100.servicio",
		"com.qapaq.ar00100.validadores",
})
@EnableJpaRepositories("com.qapaq.ar00100.jpa.queries")
@EntityScan("com.qapaq.ar00100.jpa.model")
@SpringBootApplication
@EnableEurekaClient
public class AR00100Application {

	public static void main(String[] args) {
		SpringApplication.run(AR00100Application.class, args);
	}

}
