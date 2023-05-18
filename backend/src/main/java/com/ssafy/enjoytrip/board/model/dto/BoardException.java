package com.ssafy.enjoytrip.board.model.dto;

public class BoardException extends RuntimeException{
	public BoardException() {}
	public BoardException(String msg) {
		super(msg);
	}
}
