package com.ssafy.enjoytrip.trip.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttractionInfo {
    private int contentId;
    private int contentTypeId;
    private String title;
    private String address1;
    private String address2;
    private String zipcode;
    private String tel;
    private String firstImage;
    private String firstImage2;
    private int readCount;
    private int sidoCode;
    private int gugunCode;
    private double latitude;
    private double longitude;
    private String mLevel;
    private String overview;
}
