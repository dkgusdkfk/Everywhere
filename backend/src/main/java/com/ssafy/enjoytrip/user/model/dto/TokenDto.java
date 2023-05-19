package com.ssafy.enjoytrip.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class TokenDto {
    private String accessToken;
    private String refreshToken;
    private Long expireTime;
    @Getter
    @AllArgsConstructor
    public static class Request {
        private String accessToken;
        private String refreshToken;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private String accessToken;
        private Long expireTime;
    }
}
