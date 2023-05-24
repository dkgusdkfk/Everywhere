package com.ssafy.enjoytrip.qna.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Qna {
	private int qnaId;
	private String userId;
	private String title;
	private String content;
	private int hit;
	private String registerTime;
}
