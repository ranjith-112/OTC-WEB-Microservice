package com.deleteevent.exceptionhandling;

import org.springframework.http.HttpStatus;

public class EventServiceException  extends RuntimeException{
	private final HttpStatus httpStatus;
	public EventServiceException(String message,HttpStatus httpStatus) {
		
        super(message);
        this.httpStatus = httpStatus;
        
	}
	 public HttpStatus getHttpStatus() {
	        return httpStatus;
	    }
}
