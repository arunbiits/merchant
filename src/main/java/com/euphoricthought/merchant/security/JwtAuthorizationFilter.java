/**
 * 
 */
package com.euphoricthought.merchant.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.euphoricthought.merchant.security.JwtProperties;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.euphoricthought.merchant.model.User;
import com.euphoricthought.merchant.model.UserPrincipal;
import com.euphoricthought.merchant.respository.UserRepository;

/**
 * @author bosco
 *
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

	UserRepository userRepository;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(JwtProperties.TOKEN_HEADER);
		if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		Authentication authentication = getUsernamePasswordAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
		String token = request.getHeader(JwtProperties.TOKEN_HEADER);
		if(token != null) {
			String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET_KEY.getBytes()))
					 			.build()
					 			.verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
					 			.getSubject();
			if(username != null) {
				Optional<User> userOptional = userRepository.findByUsername(username);
				if(userOptional.isPresent()) {
					UserPrincipal userPrincipal = new UserPrincipal(userOptional.get());
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,userPrincipal.getAuthorities());
					return authentication;
				}
			}
		}
		return null;
	}

}
