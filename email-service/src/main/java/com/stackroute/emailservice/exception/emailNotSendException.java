package com.stackroute.emailservice.exception;

public class emailNotSendException extends Exception{

	private static final long serialVersionUID = 1L;
	
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public emailNotSendException(String message) {
		super();
		this.message = message;
	}
	

}
