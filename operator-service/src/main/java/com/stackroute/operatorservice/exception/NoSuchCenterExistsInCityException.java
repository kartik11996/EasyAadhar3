package com.stackroute.operatorservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "No Such Center Exits in this city")
public class NoSuchCenterExistsInCityException extends Exception{
}
