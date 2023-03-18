package com.qapaq.ga00100.seguridad;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.qapaq.ConstantesTools;
import com.qapaq.filter.AuthenticationFilter;
import com.qapaq.filter.AuthorizationFilter;
import com.qapaq.ga00100.ConstantesGA00100;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Seguridad extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${spring.application.name}")
    private String appName;


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
     * http.authorizeRequests().antMatchers(HttpMethod.GET,
     * "/modulos/**").hasRole("ADM");
     * http.authorizeRequests().antMatchers(HttpMethod.PUT,
     * "/modulos/**").hasRole("ADM");
     * http.authorizeRequests().antMatchers(HttpMethod.POST,
     * "/modulos/**").hasRole("ADM");
     * http.authorizeRequests().antMatchers(HttpMethod.DELETE,
     * "/modulos/**").hasRole("ADM");
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
        http.authorizeRequests().antMatchers("/login/**").permitAll();        
        http.authorizeRequests().antMatchers("/swagger-ui/**").permitAll();

        List<VPermisoRol> listaVPermisoRol = vPermisoRolServicio.findByNickAndIndiceModulo(appName);
        for (VPermisoRol vpr : listaVPermisoRol) {
            // TODO: Quitar luego de tener los permisos claros.
            log.warn(vpr.toString());
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