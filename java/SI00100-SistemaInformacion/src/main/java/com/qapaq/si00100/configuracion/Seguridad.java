package com.qapaq.si00100.configuracion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.qapaq.ca00100.jpa.model.VPermisoRolCat;
import com.qapaq.ca00100.servicio.VPermisoRolServicioCat;
import com.qapaq.filter.AuthenticationFilter;
import com.qapaq.filter.AuthorizationFilter;
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

    @Autowired
    private final UserDetailsService userDetailsService;
    
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.servlet.context-path}")
    private String contexto;

    @Autowired
    private VPermisoRolServicioCat vPermisoRolServicioCat;

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
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManagerBean());
        authenticationFilter.setFilterProcessesUrl("/login");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        List<VPermisoRolCat> listaVPermisoRolCat = vPermisoRolServicioCat.findByNickAndIndiceModulo(appName);
        for (VPermisoRolCat vpr : listaVPermisoRolCat) {
            http.authorizeRequests().antMatchers(HttpMethod.GET, vpr.getUrl()).hasAuthority(vpr.getRol());

            if (vpr.getCrear() > 0) {
                http.authorizeRequests().antMatchers(HttpMethod.POST, vpr.getUrl()).hasAuthority(vpr.getRol());
            }
            if (vpr.getActualizar() > 0) {
                http.authorizeRequests().antMatchers(HttpMethod.PUT, vpr.getUrl()).hasAuthority(vpr.getRol());
                http.authorizeRequests().antMatchers(HttpMethod.PATCH, vpr.getUrl()).hasAuthority(vpr.getRol());
            }
            if (vpr.getBorrar() > 0) {
                http.authorizeRequests().antMatchers(HttpMethod.DELETE, vpr.getUrl()).hasAuthority(vpr.getRol());
            }
        }

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