package com.login.jwt.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.login.configuration.GlobalConfiguration;
import com.login.exceptionhandling.InvalidCredentialException;
import com.login.exceptionhandling.TokenExpiredException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtFilter extends OncePerRequestFilter {
	Logger logger = LoggerFactory.getLogger(JwtFilter.class);
	@Autowired
	private TokenManager tokenManager;
	@Autowired
	private UserDetailsService userDetailsService;
	private String accessToken = null;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String tokenHeader = request.getHeader("Authorization");
		logger.debug("tokenHeader from request " + tokenHeader);
		
		
		filterChain.doFilter(request, response);
	}
}
