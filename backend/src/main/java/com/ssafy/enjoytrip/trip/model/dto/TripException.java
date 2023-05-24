package com.ssafy.enjoytrip.trip.model.dto;

public class TripException extends RuntimeException {

    public TripException() {
    }
    public TripException(String msg) {
        super(msg);
    }

}
