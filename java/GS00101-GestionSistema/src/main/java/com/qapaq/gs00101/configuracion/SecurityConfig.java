package com.qapaq.gs00101.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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

        /**
         * Configuracion de seguridad de la aplicacion.
         * 
         * Permite la configuracion de actuator para que pueda ser administrado.
         * 
         * @param http Objeto de configuracion de seguridad de la aplicacion.
         * @throws Exception
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests(authorizeRequests -> authorizeRequests
                                .antMatchers("/actuator/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

                http.cors().and().csrf().disable();
        }

        @Bean
        CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
                configuration.setAllowedMethods(
                                Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS", "HEAD"));
                configuration.setAllowCredentials(true);
                configuration.setAllowedHeaders(Arrays.asList("Authorization", "Requestor-Type"));
                configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));
                configuration.setMaxAge(3600L);
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }
}