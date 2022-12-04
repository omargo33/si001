package com.qapaq.gs00100.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.qapaq.filter.AuthenticationFilter;
import com.qapaq.filter.AuthorizationFilter;
import com.qapaq.gs00100.jpa.model.Usuario;
import com.qapaq.gs00100.servicio.UsuarioServicio;

import lombok.RequiredArgsConstructor;

/**
 * Clase para configurar la seguridad de la aplicación.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-19
 * 
 * @see https://www.youtube.com/watch?v=VVn9OG9nfH0
 * @see security
 * 
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Seguridad extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Value("${server.servlet.context-path}")
    private String contexto;

    /**
     * Método para configurar la autenticación.
     * 
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * Método para configurar la seguridad de la aplicación.
     * 
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManagerBean()) {
            @Override
            public String generarMensaje(String userName) {

                Usuario usuario= usuarioServicio.findByNick(userName);


                return "{error: 'E1222145 holaaaa!!! '" + usuario.getIdUsuario() + "}";
            }
        };
        authenticationFilter.setFilterProcessesUrl("/login");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login/**").permitAll();
        // TODO: para poder cambiar los datos del monitor se debe permitir el acceso a
        // todos
        // TODO: http.authorizeRequests().antMatchers("/monitores/**").permitAll();
        // TODO:
        // http.authorizeRequests().antMatchers("/monitores/**").hasAuthority("ROLE_MONITOR");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(authenticationFilter);
        http.addFilterBefore(new AuthorizationFilter(contexto), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Método para encriptar la contraseña.
     * 
     * @return
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }   
}