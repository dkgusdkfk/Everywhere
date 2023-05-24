package com.ssafy.enjoytrip.qna.model.dto;

public class QnaException extends RuntimeException{
	public QnaException() {}
	public QnaException(String msg) {
		super(msg);
	}
}
