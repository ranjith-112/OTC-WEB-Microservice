package com.login.jwt.utils;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.login.configuration.GlobalConfiguration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenManager {
	Logger logger = LoggerFactory.getLogger(TokenManager.class);
	public Key secret_key = null;
	@Value("${secret_key_from_properties}")
	private String secret_key_from_properties=null;

	public String generateToken(UserDetails userDetails,String role) {
		
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		secret_key = key;
		JwtBuilder jwtBuilder = Jwts.builder();
		jwtBuilder.setSubject(userDetails.getUsername()).claim("role", role);
		jwtBuilder.setIssuedAt(new Date(System.currentTimeMillis()));
		jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000));// 360 is sec=6 min
		jwtBuilder.signWith(SignatureAlgorithm.HS256,secret_key_from_properties);
		String jwtToken = jwtBuilder.compact();
		return jwtToken;
	}

	public String getUsernameFromToken(String token) {
		if(secret_key_from_properties==null) {
			logger.info("key is null");
		}
//		final  = Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody(); // parseClaimsJws																							// split the
		  Claims claims = Jwts.parserBuilder().setSigningKey(secret_key_from_properties).build().parseClaimsJws(token).getBody();																							// token header
																										// and payload,
																										// signature
		logger.info("retrive the subject or username from Claims object " + claims.getSubject());
		logger.info("retrive the role from Claims object " + claims.get("role", String.class));
		return claims.getSubject(); // return the username
	}
	public boolean validateJwtToken(String authToken) {
			logger.debug("executing  validateJwtToken method");
			Jwts.parserBuilder().setSigningKey(secret_key_from_properties).build().parseClaimsJws(authToken);
			return true;
	}
}
