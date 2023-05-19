package com.ssafy.enjoytrip.notice.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notice {
	private int noticeId;
	private String adminId;
	private String title;
	private String content;
	private int hit;
	private String registerTime;
}
