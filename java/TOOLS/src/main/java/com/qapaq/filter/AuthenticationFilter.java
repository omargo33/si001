package com.qapaq.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.qapaq.SeguridadesConstantes;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Clase para personalizar el filtro de autenticación.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-18
 * 
 * @see https://www.youtube.com/watch?v=VVn9OG9nfH0
 * @see security
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	/**
	 * Metodo para procedar a la salida de un error en la autenticación.
	 * 
	 * @param userName
	 * @return
	 */
	public String generarMensaje(String userName) {
		return "{error: 'E1222145'" + userName + "}";
	}

	/**
	 * Constructor de la clase.
	 * 
	 * @param authenticationManager
	 */
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	/**
	 * Método para autenticar al usuario.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				userName,
				password);
		this.setDetails(request, authenticationToken);
		return authenticationManager.authenticate(authenticationToken);
	}

	/**
	 * Método para generar el token de autenticación.
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @param authResult
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain, Authentication authResult)
			throws IOException, ServletException {

		User user = (User) authResult.getPrincipal();
		Algorithm algorithm = Algorithm.HMAC256(SeguridadesConstantes.getTokenSecret());

		String accessToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
				.withIssuer(request.getRequestURL().toString())
				.withClaim(SeguridadesConstantes.ROLES_STRING,
						user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.sign(algorithm);

		String refreshToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
				.withIssuer(request.getRequestURL().toString())
				.sign(algorithm);

		response.setHeader(SeguridadesConstantes.ACCESS_TOKEN, accessToken);
		response.setHeader(SeguridadesConstantes.REFRESH_TOKEN, refreshToken);
	}

	/**
	 * Método para proceder a la salida de un error en la autenticación.
	 * 
	 * @param request
	 * @param response
	 * @param failed
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		String userName = request.getParameter("username");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		response.getWriter().write(generarMensaje(userName));
	}
}