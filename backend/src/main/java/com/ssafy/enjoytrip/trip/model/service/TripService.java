package com.ssafy.enjoytrip.trip.model.service;

import com.ssafy.enjoytrip.trip.model.dto.*;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

public interface TripService {

    JSONObject getAttractionList(SearchRequest request);

    List<Sido> getSidoList();


    JSONObject getGugunList(int sidoCode);


    JSONObject getDetailInfo(int contentId);

    public List<HotPlaceResponse> getHotPlaces();

    public void hotRegist(int id);

    public AddressResponse getAddress(int sidoCode, int gugunCode);

    List<AttractionInfo> recommendAttractionList(int sidoCode, int gugunCode);
}
