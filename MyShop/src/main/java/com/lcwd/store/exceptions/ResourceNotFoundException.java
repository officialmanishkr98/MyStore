package com.lcwd.store.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	
	//private String message;
	//other information
	
	public ResourceNotFoundException(String message) {
		super(message);
		//this.message = message;
	}
	
	public ResourceNotFoundException() {
		super("Resource not found on server !!");
		
	}
}
