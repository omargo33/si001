package com.qapaq.si.background.filtro;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthenticationFiltro extends UsernamePasswordAuthenticationFilter{
        
        private final AuthenticationManager authenticationManager;

        public AuthenticationFiltro(AuthenticationManager authenticationManager) {
            this.authenticationManager = authenticationManager;
        }

        @Override
        public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
            
            try {
                
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
                
                return authenticationManager.authenticate(authToken);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
            super.successfulAuthentication(request, response, chain, authResult);
        }
        
    
}
