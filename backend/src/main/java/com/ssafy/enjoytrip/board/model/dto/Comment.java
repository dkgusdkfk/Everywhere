package com.ssafy.enjoytrip.board.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    private int boardId;
    private int commentId;
    private String userId;
    private String content;
    private Date registerTime;
}
