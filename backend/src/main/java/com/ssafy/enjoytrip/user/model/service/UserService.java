package com.ssafy.enjoytrip.user.model.service;


import com.ssafy.enjoytrip.user.model.dto.LoginRequest;
import com.ssafy.enjoytrip.user.model.dto.User;

public interface UserService {
	User login(LoginRequest request) ;
	User search(String id) ;
	void regist(User user) ;
	void update(User user) ;
	void remove(String id) ;
	User findPassword(String id, String email);
	int idCheck(String id);
}
