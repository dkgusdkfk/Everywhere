package com.ssafy.enjoytrip.trip.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TripPlan {
    private int planId;
    private String userId;
    private List<TripPlanListDto> planList;
    private int distance;
    private int cycleTime;
    private int walkTime;
    private String title;
    public TripPlan(TripPlanResponse trip, List<TripPlanListDto> list){
        this.planList=list;
        this.planId= trip.getPlanId();
        this.userId=trip.getUserId();
        this.title=trip.getTitle();
        this.distance=trip.getDistance();
        this.cycleTime=trip.getCycleTime();
        this.walkTime=trip.getWalkTime();
    }
}
