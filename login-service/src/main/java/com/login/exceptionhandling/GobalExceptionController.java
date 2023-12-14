package com.login.exceptionhandling;

import javax.naming.AuthenticationException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@ControllerAdvice
public class GobalExceptionController {

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException accessDeniedException) {
		return new ResponseEntity<Object>("Access denied,Only Admin can add event", HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler( UsernameNotFoundException1.class)
	public ResponseEntity<Object> handleUserNotFoundException(UsernameNotFoundException1 userNotFoundException) {
		return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
	 @ExceptionHandler(EventServiceException.class)
	    public ResponseEntity<Object> handleEventServiceException(EventServiceException e) {
	        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
	    }
	 @ExceptionHandler(DataBaseDownException.class)
	    public ResponseEntity<String> handleDatabaseDownException(DataAccessException ex) {
	        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ex.getMessage());
	    }
	    @ExceptionHandler(TokenExpiredException.class)
	    public ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException ex) {
	        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body(ex.getMessage());
	    }
	    @ExceptionHandler(InvalidCredentialException.class)
	    public ResponseEntity<Object> handleInvalidCredentialException(InvalidCredentialException ex) {
	        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body(ex.getMessage());
	    }
	 
}
