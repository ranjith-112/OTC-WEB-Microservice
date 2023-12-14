package com.updateevent.jwt.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.updateevent.dto.UserDTO;
import com.updateevent.exceptionhandling.InvalidCredentialException;
import com.updateevent.exceptionhandling.TokenExpiredException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtFilter extends OncePerRequestFilter {
	Logger logger = LoggerFactory.getLogger(JwtFilter.class);
	@Autowired
	private TokenManager tokenManager;
	private String accessToken = null;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String tokenHeader = request.getHeader("Authorization");
		logger.debug("tokenHeader from request " + tokenHeader);
		if (StringUtils.hasText(tokenHeader) && tokenHeader.startsWith("Bearer ")) {
			accessToken = tokenHeader.substring(7);
			try {

				if (tokenManager.validateJwtToken(accessToken)) {
					UserDTO userDTO = tokenManager.getUserDTOFromToken(accessToken);
					System.out.println("Role from JWT token " +userDTO.getRole());
					logger.debug("Role from JWT token " +userDTO.getRole() );
					logger.debug("username from JWT token " +userDTO.getUsername() );
					if (StringUtils.hasText(userDTO.getUsername())
							&& SecurityContextHolder.getContext().getAuthentication() == null) {
						UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
								userDTO, null, userDTO.getAuthorities());
						logger.info("current user is Role" ,userDTO.getAuthorities());
						System.out.println("current user is Role" +userDTO.getAuthorities());
						String P=userDTO.getRole();
						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
						logger.info("current user is authenticated");
					}
				}
			} catch (IllegalArgumentException e) {
				logger.error("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				throw new TokenExpiredException("JWT Token was expired");
			} catch (SignatureException | MalformedJwtException e) {
				throw new InvalidCredentialException("JWT token is invalid.");
			}
		} else {
			logger.info("Bearer String not found in token");
		}
		filterChain.doFilter(request, response);
	}
}
