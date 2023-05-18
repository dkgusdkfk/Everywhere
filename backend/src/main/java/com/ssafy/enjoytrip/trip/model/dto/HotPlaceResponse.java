package com.ssafy.enjoytrip.trip.model.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HotPlaceResponse {
    private int contentId;
    private String title;
    private String address1;
    private String address2;
    //추천수
    private String count;
    private String imgPath;
}
