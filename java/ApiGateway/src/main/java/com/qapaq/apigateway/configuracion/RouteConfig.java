package com.qapaq.apigateway.configuracion;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
        .route(r -> r.path("/parametros/**")
                .uri("lb://GS00110"))

        .route(r -> r.path("/consumer/**")
                .uri("lb://SECOND-SERVICE"))
        .build();
    }
}
