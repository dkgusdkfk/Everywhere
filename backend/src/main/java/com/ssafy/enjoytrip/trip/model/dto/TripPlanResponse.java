package com.ssafy.enjoytrip.trip.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TripPlanResponse {
    private int planId;
    private String userId;
    private int distance;
    private int cycleTime;
    private int walkTime;
    private String title;
}
