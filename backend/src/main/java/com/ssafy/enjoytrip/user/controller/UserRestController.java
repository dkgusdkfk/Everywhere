package com.ssafy.enjoytrip.user.controller;

import com.ssafy.enjoytrip.security.JwtService;
import com.ssafy.enjoytrip.trip.model.service.TripService;
import com.ssafy.enjoytrip.user.model.dto.FindPasswordRequest;
import com.ssafy.enjoytrip.user.model.dto.LoginRequest;
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

@RequestMapping("/rest/user")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Slf4j
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final TripService tripService;
    private final JwtService jwtService;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";


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
    public ResponseEntity<?> findPassword(@RequestBody FindPasswordRequest request){
        return new ResponseEntity<>(userService.findPassword(request),HttpStatus.OK);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            User loginUser = userService.login(request);
            if (loginUser != null) {
                String accessToken = jwtService.createAccessToken("userid", loginUser.getId());// key, data
                String refreshToken = jwtService.createRefreshToken("userid", loginUser.getId());// key, data
                userService.saveRefreshToken(request.getId(), refreshToken);
                log.debug("로그인 accessToken 정보 : {}", accessToken);
                log.debug("로그인 refreshToken 정보 : {}", refreshToken);
                resultMap.put("access-token", accessToken);
                resultMap.put("refresh-token", refreshToken);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put("message", FAIL);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            log.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
    @GetMapping("/logout/{id}")
    public ResponseEntity<?> removeToken(@PathVariable("id") String id) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            userService.deleRefreshToken(id);
            resultMap.put("message", SUCCESS);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            log.error("로그아웃 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);

    }

    @ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody LoginRequest loginUser, HttpServletRequest request)
            throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        String token = request.getHeader("refresh-token");
        log.debug("token : {}, loginUser : {}", token, loginUser);
        if (jwtService.checkToken(token)) {
            if (token.equals(userService.getRefreshToken(loginUser.getId()))) {
                String accessToken = jwtService.createAccessToken("userid", loginUser.getId());
                log.debug("token : {}", accessToken);
                log.debug("정상적으로 액세스토큰 재발급!!!");
                resultMap.put("access-token", accessToken);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            }
        } else {
            log.debug("리프레쉬토큰도 사용불!!!!!!!");
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<>(resultMap, status);
    }
}
