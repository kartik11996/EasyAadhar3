package com.stackroute.customerservice.exception;

public class CustomerNotFoundException extends Exception{

    private final String errorMessage;

    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}


