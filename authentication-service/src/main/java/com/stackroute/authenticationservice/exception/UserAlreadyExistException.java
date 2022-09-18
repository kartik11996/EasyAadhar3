package com.stackroute.authenticationservice.exception;

public class UserAlreadyExistException extends Exception{
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public UserAlreadyExistException(String msg) {
		super();
		this.msg = msg;
	}
	

}
