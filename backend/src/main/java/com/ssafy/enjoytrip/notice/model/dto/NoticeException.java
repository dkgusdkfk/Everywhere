package com.ssafy.enjoytrip.notice.model.dto;

public class NoticeException extends RuntimeException{
	public NoticeException() {}
	public NoticeException(String msg) {
		super(msg);
	}
}
