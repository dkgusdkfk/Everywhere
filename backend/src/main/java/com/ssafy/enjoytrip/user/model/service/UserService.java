package com.ssafy.enjoytrip.user.model.service;


import com.ssafy.enjoytrip.user.model.dto.FindPasswordRequest;
import com.ssafy.enjoytrip.user.model.dto.LoginRequest;
import com.ssafy.enjoytrip.user.model.dto.User;

public interface UserService {
	User login(LoginRequest request) ;
	User search(String id) ;
	void register(User user) ;
	void update(User user) ;
	void remove(String id) ;
	User findPassword(FindPasswordRequest request);
	int idCheck(String id);
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
}
