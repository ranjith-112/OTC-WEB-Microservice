package com.login.jwt.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{
	Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		Exception exception= (Exception)request.getAttribute("exception");
		if(exception!=null) {
			logger.info("inside the getattribute ");
			byte[] message=exception.toString().getBytes();
			response.getOutputStream().write(message);
		}
		else{
			logger.info("inside the authexception ");
			response.getOutputStream().write(authException.toString().getBytes());
		}
	}
}
