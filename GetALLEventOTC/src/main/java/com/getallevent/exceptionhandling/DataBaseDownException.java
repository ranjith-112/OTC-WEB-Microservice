package com.getallevent.exceptionhandling;

public class DataBaseDownException extends RuntimeException{
	public DataBaseDownException(String message) {
        super(message);
	}
}
