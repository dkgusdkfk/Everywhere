package com.ssafy.enjoytrip.user.model.dao;


import com.ssafy.enjoytrip.user.model.dto.User;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;


@Repository
public interface UserDao {
	User search(String id) throws SQLException;
	void regist(User user) throws SQLException;
	void update(User user) throws SQLException;
	void remove(String id) throws SQLException;
	User findPassword(String id, String email) throws SQLException;
}
