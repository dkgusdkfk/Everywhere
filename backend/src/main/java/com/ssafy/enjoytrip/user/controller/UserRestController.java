package com.ssafy.enjoytrip.user.controller;

import com.ssafy.enjoytrip.security.JwtService;
import com.ssafy.enjoytrip.user.model.dto.FindPasswordRequest;
import com.ssafy.enjoytrip.user.model.dto.LoginRequest;
import com.ssafy.enjoytrip.user.model.dto.TokenDto;
import com.ssafy.enjoytrip.user.model.dto.User;
import com.ssafy.enjoytrip.user.model.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Slf4j
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final JwtService jwtService;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String USERID = "id";
    private static final String MESSAGE = "message";


    @GetMapping("/{id}")
    public ResponseEntity<?> search(@PathVariable String id) {
        User user = userService.search(id);
        if (user == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.register(user);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        userService.remove(id);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PostMapping("find")
    public ResponseEntity<?> findPassword(@RequestBody FindPasswordRequest request) {
        return new ResponseEntity<>(userService.findPassword(request), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {
            User loginUser = userService.login(request);
            if (loginUser != null) {
                TokenDto tokenDto = jwtService.create(USERID, loginUser.getId());
                userService.saveRefreshToken(request.getId(), tokenDto.getRefreshToken());
                resultMap.put("access-token", tokenDto.getAccessToken());
                resultMap.put("refresh-token", tokenDto.getRefreshToken());
                resultMap.put(MESSAGE, SUCCESS);
                log.debug("=-=-=--==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-{}",resultMap);
                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put(MESSAGE, FAIL);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            log.error("로그인 실패 : {}", e);
            resultMap.put(MESSAGE, e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
    @GetMapping("/logout/{id}")
    public ResponseEntity<?> removeToken(@PathVariable("id") String id) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {
            userService.deleRefreshToken(id);
            resultMap.put(MESSAGE, SUCCESS);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            log.error("로그아웃 실패 : {}", e);
            resultMap.put(MESSAGE, e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);

    }

    @ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody LoginRequest loginUser, HttpServletRequest request)
            throws Exception {

        String refreshToken = request.getHeader("refresh-token");
//        String refreshToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyaWQiOiJhZG1pbiIsImV4cCI6MTY4NTA4Mjg2Miwic3ViIjoicmVmcmVzaC1Ub2tlbiJ9.rdSspjP_V1_JP70mGdvW43_NJHjc58HGmeMDoCr5490";
        String id = jwtService.getUserId();
        if (!jwtService.checkToken(refreshToken) || !id.equals(loginUser.getId())) throw new Exception("토큰 사용 불가");
        return new ResponseEntity<>(jwtService.createAccessToken(USERID, loginUser.getId()), HttpStatus.ACCEPTED);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<Map<String, Object>> getInfo(
            @PathVariable("id") String userid,
            HttpServletRequest request) {
//		log.debug("userid : {} ", userid);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        if (jwtService.checkToken(request.getHeader("access-token"))) {
            log.info("사용 가능한 토큰!!!");
            try {
//				로그인 사용자 정보.
                User user = userService.search(userid);
                resultMap.put("userInfo", user);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                log.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            log.error("사용 불가능 토큰!!!");
            resultMap.put("message", FAIL);
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
}
