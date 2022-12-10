package com.qapaq.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import com.qapaq.ConstantesTools;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	/**
	 * Metodo para procedar a la salida de un error en la autenticación, debe ser
	 * Override.
	 * 
	 * @param userName
	 * @return
	 */
	public String generarMensajeError(HttpServletRequest request) {
		String userName = request.getParameter(ConstantesTools.USER_NAME);
		String json = "";

		Map<String, Object> map = new HashMap<>();
		map.put("error", "E-GS00100-9");
		map.put("user", userName);
		try {
			json = new ObjectMapper().writeValueAsString(map);
		} catch (Exception e) {
			log.error("E-GS00100-9 {}", e.getMessage());
		}
		return json;
	}

	/**
	 * Metodo para proceder a la salida de ejecucion correcta de la autenticación,
	 * debe ser Override.
	 * 
	 * @param userName
	 */
	public void ejecutaPostIngreso(HttpServletRequest request) {
		String userName = request.getParameter(ConstantesTools.USER_NAME);
		log.warn("W-GS00100-3 {}", userName);
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
		String userName = request.getParameter(ConstantesTools.USER_NAME);
		String password = request.getParameter(ConstantesTools.PASSWORD);
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
		ejecutaPostIngreso(request);

		User user = (User) authResult.getPrincipal();
		Algorithm algorithm = Algorithm.HMAC256(ConstantesTools.getPassword());

		String accessToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
				.withIssuer(request.getRequestURL().toString())
				.withClaim(ConstantesTools.ROLES_STRING,
						user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.sign(algorithm);

		String refreshToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
				.withIssuer(request.getRequestURL().toString())
				.sign(algorithm);

		response.setHeader(ConstantesTools.ACCESS_TOKEN, accessToken);
		response.setHeader(ConstantesTools.REFRESH_TOKEN, refreshToken);
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
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		response.getWriter().write(generarMensajeError(request));
	}
}