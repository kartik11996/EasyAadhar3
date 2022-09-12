package com.stackroute.operatorservice.exception;

public class NoSuchCenterExistsException extends RuntimeException{

        private String message;

        public NoSuchCenterExistsException() {}

        public NoSuchCenterExistsException(String msg)
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
