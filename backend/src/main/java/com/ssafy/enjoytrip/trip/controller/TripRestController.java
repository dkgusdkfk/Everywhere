package com.ssafy.enjoytrip.trip.controller;

import com.ssafy.enjoytrip.trip.model.dto.SearchRequest;
import com.ssafy.enjoytrip.trip.model.service.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rest/trip")
public class TripRestController {
    private TripService tripService;
    private static final String SUCCESS = "success";

    @Autowired
    public TripRestController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(SearchRequest request) {
        return new ResponseEntity<>(tripService.getAttractionList(request), HttpStatus.OK);
    }
    @GetMapping("/sido")
    public ResponseEntity<?> sido() {
        return new ResponseEntity<>(tripService.getSidoList(), HttpStatus.OK);
    }

    @GetMapping("/gugun/{sidoCode}")
    public ResponseEntity<?> gugun(@PathVariable int sidoCode) {
        return new ResponseEntity<>(tripService.getGugunList(sidoCode), HttpStatus.OK);
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<?> detail(@PathVariable int contentId) {
        return new ResponseEntity<>(tripService.getDetailInfo(contentId), HttpStatus.OK);
    }

    @GetMapping("/hotPlace")
    public ResponseEntity<?> hotPlace() {
        return new ResponseEntity<>(tripService.getHotPlaces(), HttpStatus.OK);
    }

    @PostMapping("/hotRegist/{id}")
    public ResponseEntity<?> hotRegist(@PathVariable String id) {
        tripService.hotRegist(Integer.parseInt(id));
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }


}
