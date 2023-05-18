package com.ssafy.enjoytrip.user.model.dao;


import com.ssafy.enjoytrip.user.model.dto.FindPasswordRequest;
import com.ssafy.enjoytrip.user.model.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Map;


@Mapper
public interface UserDao {
	User search(String id) throws SQLException;
	void register(User user) throws SQLException;
	void update(User user) throws SQLException;
	void remove(String id) throws SQLException;
	User findPassword(FindPasswordRequest request) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
