package com.stackroute.authenticationservice.exception;

public class InvalidCredentialException extends Exception {
	
	String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public InvalidCredentialException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	

}
