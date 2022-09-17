package com.stackroute.customerservice.exception;

public class CustomerAlreadyExistsException extends Exception{

    private final String errorMessage;

    private static final long serialVersionUID = 1L;

    public CustomerAlreadyExistsException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

}