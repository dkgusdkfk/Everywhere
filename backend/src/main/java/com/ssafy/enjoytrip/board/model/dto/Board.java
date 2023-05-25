package com.ssafy.enjoytrip.board.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {
	private int boardId;
	private String userId;
	private String title;
	private String content;
	private int hit;
	private String registerTime;
	private int planId;
}
