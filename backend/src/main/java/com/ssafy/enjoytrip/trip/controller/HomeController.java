package com.ssafy.enjoytrip.trip.controller;

import com.ssafy.enjoytrip.trip.model.dto.AttractionInfo;
import com.ssafy.enjoytrip.trip.model.dto.HotPlaceResponse;
import com.ssafy.enjoytrip.trip.model.service.TripService;
import com.ssafy.enjoytrip.user.model.dto.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final TripService tripService;


    /**
     * Simply selects the home view to render by returning its name.
     */
    @GetMapping(value = {"/", "/index"})
    public String home(Locale locale, Model model, HttpSession httpSession) {
        logger.info("Welcome home! The client locale is {}.", locale);


        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);

        model.addAttribute("serverTime", formattedDate);

        User user = (User) httpSession.getAttribute("userInfo");
        List<AttractionInfo> recommendAttraction = null;
        if (user != null && user.getAddress1() != null) {
            recommendAttraction = tripService.recommendAttractionList(Integer.parseInt(user.getAddress1()), Integer.parseInt(user.getAddress2()));
        }
        model.addAttribute("recommendAttraction", recommendAttraction);

        List<HotPlaceResponse> hotPlaceList = tripService.getHotPlaces();
        model.addAttribute("hotPlaceList", hotPlaceList);

        return "index";
    }

}
