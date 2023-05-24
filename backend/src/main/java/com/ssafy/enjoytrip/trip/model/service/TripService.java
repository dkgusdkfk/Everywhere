package com.ssafy.enjoytrip.trip.model.service;

import com.ssafy.enjoytrip.trip.model.dto.*;
import org.json.simple.JSONObject;

import java.util.List;

public interface TripService {

    JSONObject getAttractionList(SearchRequest request);

    List<Sido> getSidoList();

    JSONObject getGugunList(int sidoCode);

    JSONObject getDetailInfo(int contentId);

    List<HotPlaceResponse> getHotPlaces();

    void hotRegist(HotPlaceRequest request);

    AddressResponse getAddress(int sidoCode, int gugunCode);

    List<AttractionInfo> recommendAttractionList(int sidoCode, int gugunCode);
}
