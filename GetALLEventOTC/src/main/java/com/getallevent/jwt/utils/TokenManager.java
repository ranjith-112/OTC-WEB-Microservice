package com.getallevent.jwt.utils;

import java.security.Key;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.getallevent.dto.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class TokenManager {
	Logger logger = LoggerFactory.getLogger(TokenManager.class);
	public Key secret_key = null;
	@Value("${secret_key_from_properties}")
	private String secret_key_from_properties=null;
	@Autowired
	private UserDTO userDTO;

	public UserDTO getUserDTOFromToken(String token) {
		if(secret_key_from_properties==null) {
			logger.info("key is null");
		}
//		final  = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody(); // parseClaimsJws																							// split the
		  Claims claims = Jwts.parserBuilder().setSigningKey(secret_key_from_properties).build().parseClaimsJws(token).getBody();																							// token header
																										// and payload,
																										// signature
		logger.info("retrive the subject or username from Claims object " + claims.getSubject());
		
		System.out.println("retrive the role from Claims object " + claims.get("role", String.class));
		String role = claims.get("role", String.class);
		userDTO.setUsername(claims.getSubject());
		userDTO.setRole(role);
		return userDTO; 
	}
	public boolean validateJwtToken(String authToken) {
			logger.debug("executing  validateJwtToken method");
			Jwts.parserBuilder().setSigningKey(secret_key_from_properties).build().parseClaimsJws(authToken);
			return true;
	}
}
