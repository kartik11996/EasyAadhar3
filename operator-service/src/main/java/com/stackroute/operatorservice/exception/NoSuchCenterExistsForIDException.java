package com.stackroute.operatorservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "No Such Center Exists for this ID")
@Data
public class NoSuchCenterExistsForIDException extends  RuntimeException{
    private String msg;

    public NoSuchCenterExistsForIDException(String msg){
        super(msg);
        this.msg=msg;
    }
    public NoSuchCenterExistsForIDException(){

    }


}