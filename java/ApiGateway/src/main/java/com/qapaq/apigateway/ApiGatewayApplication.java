package com.qapaq.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * ApiGatewayApplication
 * 
 * @author o.velez@qapaq.io
 * @since 2023-04-30
 */
@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}	
}
