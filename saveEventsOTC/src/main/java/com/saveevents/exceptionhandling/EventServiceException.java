package com.saveevents.exceptionhandling;

import org.springframework.http.HttpStatus;

public class EventServiceException  extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final HttpStatus httpStatus;
	public EventServiceException(String message,HttpStatus httpStatus) {
		
        super(message);
        this.httpStatus = httpStatus;
        
	}
	 public HttpStatus getHttpStatus() {
	        return httpStatus;
	    }
}
