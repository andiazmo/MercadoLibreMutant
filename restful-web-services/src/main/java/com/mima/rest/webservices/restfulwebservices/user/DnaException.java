package com.mima.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DnaException extends RuntimeException {
	
	public DnaException(String message) {
		super(message);
		
	}

}
