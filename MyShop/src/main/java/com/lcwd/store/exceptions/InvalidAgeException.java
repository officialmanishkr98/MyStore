package com.lcwd.store.exceptions;

public class InvalidAgeException extends RuntimeException {

	public InvalidAgeException() {
		super("Age is Invalid");
		
	}
	
	public InvalidAgeException(String message) {
		super(message);
		
	}	 

}
