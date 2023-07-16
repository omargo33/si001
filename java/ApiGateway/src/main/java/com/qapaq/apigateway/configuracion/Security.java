package com.qapaq.apigateway.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


/**
 * Clase para configurar la Security
 * 
 * @author o.velez@qapaq.io
 * @since 1.0
 * @see "https://www.youtube.com/watch?v=nGcVzBMjEGg&list=PLSVW22jAG8pDY3lXXEv1hKVIAlnJ9nDN_&index=7"
 */
@Configuration
@EnableWebFluxSecurity
public class Security {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchange -> exchange.anyExchange().authenticated())
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);

        http.csrf().disable();
        return http.build();
    }
}