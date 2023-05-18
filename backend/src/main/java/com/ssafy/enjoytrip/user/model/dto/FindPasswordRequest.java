package com.ssafy.enjoytrip.user.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FindPasswordRequest {
    private String id;
    private String email;
}
