package com.ssafy.enjoytrip.trip.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HotPlaceRequest {
    private int contentId;
    private String userId;
}
