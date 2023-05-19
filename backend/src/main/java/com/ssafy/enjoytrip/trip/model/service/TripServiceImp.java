package com.ssafy.enjoytrip.trip.model.service;

import com.ssafy.enjoytrip.trip.model.dao.TripDao;
import com.ssafy.enjoytrip.trip.model.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TripServiceImp implements TripService {
    private final TripDao tripDao;

    @Override
    public JSONObject getAttractionList(SearchRequest request) {
        try {
            List<AttractionInfo> attractionList = tripDao.getAttractionList(request);
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            for (AttractionInfo attractionDto : attractionList) {
                JSONObject dto = new JSONObject();
                dto.put("contentId", attractionDto.getContentId());
                dto.put("title", attractionDto.getTitle());
                dto.put("imgPath", attractionDto.getFirstImage());
                dto.put("address1", attractionDto.getAddress1());
                dto.put("address2", attractionDto.getAddress2());
                dto.put("zipcode", attractionDto.getZipcode());
                dto.put("tel", attractionDto.getTel());
                dto.put("latitude", attractionDto.getLatitude());
                dto.put("longitude", attractionDto.getLongitude());
                dto.put("sidoCode", attractionDto.getSidoCode());
                dto.put("gugunCode", attractionDto.getGugunCode());
                dto.put("overview", attractionDto.getOverview());
                jsonArray.add(dto);
            }

            jsonObject.put("attractionList", jsonArray);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public JSONObject getGugunList(int sidoCode) {
        try {
            List<Gugun> gugunList = tripDao.getGugunList(sidoCode);
            JSONObject jsonObjectGugun = new JSONObject();
            JSONArray jsonArrayGugun = new JSONArray();
            for (Gugun gugunDto : gugunList) {
                JSONObject dto = new JSONObject();
                dto.put("gugunCode", gugunDto.getGugunCode());
                dto.put("gugunName", gugunDto.getGugunName());
                jsonArrayGugun.add(dto);
            }
            jsonObjectGugun.put("gugunList", jsonArrayGugun);
            return jsonObjectGugun;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Sido> getSidoList() {
        try {
            return tripDao.getSidoList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public JSONObject getDetailInfo(int contentId) {
        try {
            AttractionInfo find = tripDao.getDetailInfo(contentId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", find.getTitle());
            jsonObject.put("address1", find.getAddress1());
            jsonObject.put("address2", find.getAddress2());
            jsonObject.put("imagePath", find.getFirstImage());
            jsonObject.put("zipCode", find.getZipcode());
            jsonObject.put("telNumber", find.getTel());
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<HotPlaceResponse> getHotPlaces() {
        try {
            return tripDao.getHotPlaces();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void hotRegist(int id) {
        try {
            if (tripDao.hotSearch(id) == null) {
                tripDao.hotRegist(id);
            } else {
                tripDao.hotUpdate(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AddressResponse getAddress(int sidoCode, int gugunCode) {
        try {
            return tripDao.getAddress(sidoCode, gugunCode);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AttractionInfo> recommendAttractionList(int sidoCode, int gugunCode) {
        try {
            return tripDao.recommendAttractionList(sidoCode, gugunCode);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
