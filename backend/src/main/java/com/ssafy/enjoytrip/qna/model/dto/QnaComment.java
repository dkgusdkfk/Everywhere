package com.ssafy.enjoytrip.qna.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QnaComment {
    private int qnaId;
    private int commentId;
    private String userId;
    private String content;
    private String registerTime;
}
