package com.dharam.deloitte.exception;

public class AuthenticationException extends RuntimeException {
	
	private static final long serialVersionUID = -6198470968618298220L;

	public AuthenticationException(String message) {
        super(message);
    }
	
	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
