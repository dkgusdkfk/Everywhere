package com.ssafy.enjoytrip.security;

import com.ssafy.enjoytrip.user.model.dto.TokenDto;

import java.util.Map;

public interface JwtService {

	<T> TokenDto create(String key, T data);
	Map<String, Object> get(String key);
	String getUserId();
	boolean checkToken(String jwt);
	TokenDto.Response createAccessToken(String key, String id);
	
}
