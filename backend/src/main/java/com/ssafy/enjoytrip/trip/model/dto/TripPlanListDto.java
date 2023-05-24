package com.ssafy.enjoytrip.trip.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TripPlanListDto {
    private int planId;
    private int contentId;
    private int order;
}
