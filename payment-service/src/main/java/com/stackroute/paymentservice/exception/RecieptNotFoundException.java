package com.stackroute.paymentservice.exception;

public class RecieptNotFoundException extends RuntimeException {
	private final String errorMessage;
	
	private static final long serialVersionUID = 1L;
	
     public RecieptNotFoundException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

}
