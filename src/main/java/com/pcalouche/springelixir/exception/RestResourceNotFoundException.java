package com.pcalouche.springelixir.exception;

/**
 * Exception to throw when a REST resource is not found. Intended to be used for endpoints
 * with patterns like /resource/{id}
 */
public class RestResourceNotFoundException extends RuntimeException {

	public RestResourceNotFoundException(String message) {
		super(message);
	}

	public RestResourceNotFoundException(String message, Throwable t) {
		super(message, t);
	}

}
