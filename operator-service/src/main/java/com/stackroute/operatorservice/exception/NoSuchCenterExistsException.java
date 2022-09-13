package com.stackroute.operatorservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "No Center Exists ")
public class NoSuchCenterExistsException extends  RuntimeException{
    private String msg;

    public NoSuchCenterExistsException(String msg){
        super(msg);
        this.msg=msg;
    }
    public NoSuchCenterExistsException(){

    }

}
