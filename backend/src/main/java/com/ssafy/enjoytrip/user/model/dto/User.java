package com.ssafy.enjoytrip.user.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    private String id;
    private String password;
    private String name;
    private String email;
    private String address1; //특별시, 광역시, 도
    private String address2; //시, 군, 구
    private String token = null;
}
