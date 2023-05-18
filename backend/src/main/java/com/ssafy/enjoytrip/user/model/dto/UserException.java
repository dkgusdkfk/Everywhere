package com.ssafy.enjoytrip.user.model.dto;

public class UserException extends RuntimeException{
	public UserException() {}
	public UserException(String msg) {
		super(msg);
	}
}
