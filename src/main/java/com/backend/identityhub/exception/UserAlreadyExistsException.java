package com.backend.identityhub.exception;

public class UserAlreadyExistsException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1079410617789351382L;

	public UserAlreadyExistsException(String message) {
        super(message);
    }
}