package com.qapaq.lg00100.configuracion;

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
import com.qapaq.ca00100.ConstantesCA00100;
import com.qapaq.ca00100.jpa.model.VPermisoRol;
import com.qapaq.ca00100.servicio.VPermisoRolServicio;
import com.qapaq.filter.AuthenticationFilter;
import com.qapaq.filter.AuthorizationFilter;
import com.qapaq.lg00100.servicio.UsuarioServicio;

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

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.servlet.context-path}")
    private String contexto;

    @Autowired
    private final UserDetailsService userDetailsService;
    
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private VPermisoRolServicio vPermisoRolServicio;

    /**
     * Método para configurar la autenticación.
     * 
     * @param auth
     * @throws Exception
     */
    /*~~(Migrate manually based on https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)~~>*/@Override
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
        AuthenticationFilter authenticationFilter = crearAuthenticationFilter();
        authenticationFilter.setFilterProcessesUrl("/login");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login/**").permitAll();

        List<VPermisoRol> listaVPermisoRol = vPermisoRolServicio.findByNickAndIndiceModulo(appName);
        for (VPermisoRol vpr : listaVPermisoRol) {
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
    /*~~(Migrate manually based on https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)~~>*/@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Método para crear el filtro de autentificacion.
     * 
     * @return
     */
    private AuthenticationFilter crearAuthenticationFilter() throws Exception {

        return new AuthenticationFilter(authenticationManagerBean()) {

            @Override
            public String generarMensajeError(HttpServletRequest request) {
                String mensajeError = "W-GS00100-6";
                String userName = request.getParameter(ConstantesTools.USER_NAME);
                String ip = request.getRemoteAddr() + " " + request.getRemoteHost() + ":" + request.getRemotePort();
                String userAgent = request.getHeader("User-Agent");

                usuarioServicio.usuarioRechazado(ip, userAgent, userName, appName  );
                String estado = usuarioServicio.validarUsuarioLogin(userName);
                switch (estado) {
                    case ConstantesCA00100.TOKEN_ESTADO_USUARIO_NO_EXISTE:
                        mensajeError = "W-GS00100-6";
                        break;
                    case ConstantesCA00100.TOKEN_ESTADO_USUARIO_EXCEDE_NUMERO_INTENTOS:
                        mensajeError = "W-GS00100-7";
                        break;
                    case ConstantesCA00100.TOKEN_ESTADO_INACTIVO:
                        mensajeError = "W-GS00100-8";
                        break;
                    case ConstantesCA00100.TOKEN_ESTADO_ACTIVO:
                        mensajeError = "W-GS00100-6";
                        break;
                    case ConstantesCA00100.TOKEN_ESTADO_CREADO:
                        mensajeError = "W-GS00100-6";
                        break;
                    default:
                        mensajeError = "W-GS00100-1";
                        log.warn("W-GS00100-1 user={} estado={}", userName, estado);
                        break;
                }
                return String.format("{ \"error\": \"%s\"}", mensajeError);
            }

            /**
             * Método para ejecutar acciones después de ingresar.
             * 
             * @param request
             */
            @Override
            public void ejecutaPostIngreso(HttpServletRequest request) {
                String userName = request.getParameter(ConstantesTools.USER_NAME);
                usuarioServicio.inicialiarContadoresIngreso(userName);
            }
        };
    }
}