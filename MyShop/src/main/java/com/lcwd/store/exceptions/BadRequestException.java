package com.lcwd.store.exceptions;

import lombok.Builder;

@Builder
public class BadRequestException extends RuntimeException {
	
	public BadRequestException() {
		super("bad request exception");
	}

	public BadRequestException(String message) {
		super(message);
	}
}
