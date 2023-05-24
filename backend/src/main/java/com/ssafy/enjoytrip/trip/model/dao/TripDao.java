package com.ssafy.enjoytrip.trip.model.dao;

import com.ssafy.enjoytrip.trip.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface TripDao {

    List<AttractionInfo> getAttractionList(SearchRequest request) throws SQLException;

    List<Sido> getSidoList() throws SQLException;

    List<Gugun> getGugunList(int gugunCode) throws SQLException;

    AttractionInfo getDetailInfo(int contentId) throws SQLException;

    List<HotPlaceResponse> getHotPlaces() throws SQLException;

    HotPlace hotSearch(int id) throws SQLException;

    void hotRegist(int id) throws SQLException;

    void hotUpdatePlus(int id) throws SQLException;

    void hotUpdateMinus(int id) throws SQLException;

    AddressResponse getAddress(int sidoCode, int gugunCode) throws SQLException;

    List<AttractionInfo> recommendAttractionList(int sidoCode, int gugunCode) throws SQLException;

    int checkRecommend(HotPlaceRequest request) throws SQLException;

    void addRecommendedUser(HotPlaceRequest request) throws SQLException;

    void deleteRecommendUser(HotPlaceRequest request) throws SQLException;

    void addPlan(TripPlan request) throws SQLException;

    void addPlanList(TripPlanListDto request) throws SQLException;

    void deletePlan(int planId) throws SQLException;

    List<TripPlanListDto> getPlanListByPlanId(int planId) throws SQLException;

    TripPlanResponse getPlan(int planId) throws SQLException;
}
