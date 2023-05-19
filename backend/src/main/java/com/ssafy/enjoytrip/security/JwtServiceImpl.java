package com.ssafy.enjoytrip.security;

import com.ssafy.enjoytrip.user.model.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

	private static final String SALT = "ssafy";
	public static final long ACCESS_TOKEN_EXPIRE_TIME = 30 * 60 * 1000L;
	public static final long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 24 * 7;
	


//	AccessToken에 비해 유효기간을 길게...

	//Token 발급
	/**
	 * key : Claim에 셋팅될 key 값
	 * data : Claim에 셋팅 될 data 값
	 * subject : payload에 sub의 value로 들어갈 subject값
	 * expire : 토큰 유효기간 설정을 위한 값
	 * jwt 토큰의 구성 : header + payload + signature
	 */

	@Override
	public <T> TokenDto create(String key, T data) {

		Date now = new Date();

		String accessToken =  Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.claim(key,data)
				.setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME))        // payload "exp": 1516239022 (예시
				.setSubject("access-Token")
				.signWith(SignatureAlgorithm.HS256, this.generateKey())
				.compact(); // 직렬화 처리.


		Date refreshTokenExpiresIn = new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME);

		String refreshToken = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.claim(key,data)
				.setExpiration(refreshTokenExpiresIn)        // payload "exp": 1516239022 (예시
				.setSubject("refresh-Token")
				.signWith(SignatureAlgorithm.HS256, this.generateKey())
				.compact(); // 직렬화 처리.
		return new TokenDto(accessToken, refreshToken, REFRESH_TOKEN_EXPIRE_TIME);
	}

	// Signature 설정에 들어갈 key 생성.
	private byte[] generateKey() {
		byte[] key = null;
		// charset 설정 안하면 사용자 플랫폼의 기본 인코딩 설정으로 인코딩 됨.
		key = SALT.getBytes(StandardCharsets.UTF_8);
		return key;
	}

//	전달 받은 토큰이 제대로 생성된것인지 확인 하고 문제가 있다면 UnauthorizedException을 발생.
	@Override
	public boolean checkToken(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
//			Claims 는 Map의 구현체 형태
			log.debug("claims: {}", claims);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
//			개발환경
			return false;
		}
	}

	@Override
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String jwt = request.getHeader("access-token");
//		String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyaWQiOiJhZG1pbiIsImV4cCI6MTY4NTA4Mjg2Miwic3ViIjoicmVmcmVzaC1Ub2tlbiJ9.rdSspjP_V1_JP70mGdvW43_NJHjc58HGmeMDoCr5490";
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(SALT.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(jwt);
			log.debug("-----------try문 통과");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new UnAuthorizedException();
		}
		Map<String, Object> value = claims.getBody();
		log.info("value : {}", value);
		return value;
	}
	@Override
	public TokenDto.Response createAccessToken(String key, String id) {

		Date now = new Date();
		Date accessTokenExpiresIn = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME);
		String accessToken =  Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.claim(key, id)
				.setExpiration(accessTokenExpiresIn)        // payload "exp": 1516239022 (예시
				.setSubject("access-Token")
				.signWith(SignatureAlgorithm.HS256, this.generateKey())
				.compact(); // 직렬화 처리.


		return new TokenDto.Response(accessToken, REFRESH_TOKEN_EXPIRE_TIME);
	}

	@Override
	public String getUserId() {
		return (String) this.get("user").get("userid");
	}

}
