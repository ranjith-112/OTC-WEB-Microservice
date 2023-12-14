package com.login.exceptionhandling;

public class DataBaseDownException extends RuntimeException{
	public DataBaseDownException(String message) {
        super(message);
	}
}
