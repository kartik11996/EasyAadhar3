package com.stackroute.emailservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason="Recipent Email Not Found")
public class MailSendException extends Exception{

}
