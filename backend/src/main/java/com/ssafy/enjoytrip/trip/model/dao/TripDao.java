package com.ssafy.enjoytrip.trip.model.dao;

import com.ssafy.enjoytrip.trip.model.dto.*;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface TripDao {

	List<AttractionInfo> getAttractionList(SearchRequest request) throws SQLException;

	List<Sido> getSidoList() throws SQLException;

	List<Gugun> getGugunList(int gugunCode) throws SQLException;

	
	AttractionInfo getDetailInfo(int contentId) throws SQLException;
	
	List<HotPlaceResponse> getHotPlaces() throws SQLException;

	HotPlace hotSearch(int id) throws SQLException;

	void hotRegist(int id) throws SQLException;

	void hotUpdate(int id) throws  SQLException;

	AddressResponse getAddress(int sidoCode, int gugunCode) throws  SQLException;

	List<AttractionInfo> recommendAttractionList(int sidoCode, int gugunCode) throws SQLException;
}
