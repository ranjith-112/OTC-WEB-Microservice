package com.updateevent.exceptionhandling;

import java.nio.file.AccessDeniedException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

	/*
	 * @ExceptionHandler(EventServiceException.class) public ModelAndView
	 * handleEventServiceException(EventServiceException e) { ModelAndView
	 * modelAndView = new ModelAndView("error");
	 * modelAndView.addObject("errorMessage", e.getMessage()); return modelAndView;
	 * }
	 */
	 @ExceptionHandler(EventServiceException.class)
	    public ResponseEntity<Object> handleEventServiceException(EventServiceException e) {
	        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
	    }
	 @ExceptionHandler(DataBaseDownException.class)
	    public ResponseEntity<String> handleDatabaseDownException(DataAccessException ex) {
	        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ex.getMessage());
	    }
	 
	/*
	 * @ExceptionHandler({ DataIntegrityViolationException.class }) public
	 * ResponseEntity<Object> handleEventNotFoundException1(
	 * DataIntegrityViolationException constraintViolationException) { return new
	 * ResponseEntity<Object>
	 * ("A record with the same name, start date, and trainer already exists.",
	 * HttpStatus.CONFLICT); }
	 */
}
