package com.ssafy.enjoytrip.trip.model.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
    private int sidoCode;
    private int gugunCode;
    private int contentTypeId;
}
