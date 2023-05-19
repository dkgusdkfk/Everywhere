package com.ssafy.enjoytrip.trip.controller;

import com.ssafy.enjoytrip.trip.model.dto.HotPlaceResponse;
import com.ssafy.enjoytrip.trip.model.dto.SearchRequest;
import com.ssafy.enjoytrip.trip.model.dto.Sido;
import com.ssafy.enjoytrip.trip.model.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @ResponseBody
    @GetMapping("/search")
    public JSONObject search(@RequestParam("sidoCode") int sidoCode, @RequestParam("gugunCode") int gugunCode,
                             @RequestParam("contentTypeId") int contentTypeId) {
        return tripService.getAttractionList(new SearchRequest(sidoCode, gugunCode, contentTypeId));
    }

    @GetMapping("/recommend")
    public String recommend(Model model) {
        List<Sido> list = tripService.getSidoList();
        model.addAttribute("sidoList", list);
        return "trip/recommendByLocation";
    }

    @ResponseBody
    @GetMapping("/gugun")
    public JSONObject gugun(@RequestParam int sidoCode) {
        return tripService.getGugunList(sidoCode);
    }

    @ResponseBody
    @GetMapping("/detail")
    public JSONObject detail(@RequestParam("contentId") int contentId) {
        return tripService.getDetailInfo(contentId);
    }

    @GetMapping("/info")
    public String info() {
        return null;
    }

    @GetMapping("/hotPlace")
    public String hotPlace(Model model) {
        List<HotPlaceResponse> list = tripService.getHotPlaces();
        model.addAttribute("places", list);
        return "trip/HotPlace";
    }

    @GetMapping("/hotRegist")
    public void hotRegist(@RequestParam String id) {
        tripService.hotRegist(Integer.parseInt(id));
    }

}