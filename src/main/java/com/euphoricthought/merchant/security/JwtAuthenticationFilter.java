/**
 * 
 */
package com.euphoricthought.merchant.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.euphoricthought.merchant.model.Login;
import com.euphoricthought.merchant.model.UserPrincipal;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author bosco
 *
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManger) {
		this.authenticationManager = authenticationManger;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		Login login = null;
		try {
			login = new ObjectMapper().readValue(request.getInputStream(), Login.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), Collections.emptyList());
		Authentication authentication = authenticationManager.authenticate(token);
		return authentication;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UserPrincipal userPrincipal = (UserPrincipal) authResult.getPrincipal();
		String token = JWT.create()
						  .withSubject(userPrincipal.getUsername())
						  .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
						  .sign(Algorithm.HMAC512(JwtProperties.SECRET_KEY.getBytes()));
		response.addHeader(JwtProperties.TOKEN_HEADER, JwtProperties.TOKEN_PREFIX+token);
	}

}
