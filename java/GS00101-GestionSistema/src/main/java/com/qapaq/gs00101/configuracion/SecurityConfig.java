package com.qapaq.gs00101.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

/**
 * Objeto de configuracion de seguridad de la aplicacion.
 * 
 * @author o.velez@qapaq.io
 * @since 2023-07-16
 * 
 * @see "https://www.youtube.com/watch?v=nGcVzBMjEGg&list=PLSVW22jAG8pDY3lXXEv1hKVIAlnJ9nDN_&index=7"
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

                

        }
}