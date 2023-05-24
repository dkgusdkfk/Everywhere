package com.ssafy.enjoytrip.trip.model.service;

import com.ssafy.enjoytrip.trip.model.dto.*;
import org.json.simple.JSONObject;

import java.util.List;

public interface TripService {

    JSONObject getAttractionList(SearchRequest request);

    List<Sido> getSidoList();

    JSONObject getGugunList(int sidoCode);

    JSONObject getDetailJSON(int contentId);

    List<HotPlaceResponse> getHotPlaces();

    void hotRegist(HotPlaceRequest request);

    AddressResponse getAddress(int sidoCode, int gugunCode);

    List<AttractionInfo> recommendAttractionList(int sidoCode, int gugunCode);

    void addPlan(TripPlan request);

    void deletePlan(int planId);

    TripPlan getPlan(int planId);
    AttractionInfo getDetail(int contentId);
}
