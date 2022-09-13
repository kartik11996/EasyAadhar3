package com.stackroute.operatorservice.exception;

public class CentreAlreadyExistsException extends RuntimeException{
    private String message;


    public CentreAlreadyExistsException() {}

    public CentreAlreadyExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

