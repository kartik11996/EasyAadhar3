package com.stackroute.operatorservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "No Such Center Exists for this PinCode")
public class NoSuchCenterExistsInLocationPInException extends Exception{
}
