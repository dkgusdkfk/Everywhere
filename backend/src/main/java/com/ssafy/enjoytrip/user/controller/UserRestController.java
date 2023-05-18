package com.ssafy.enjoytrip.user.controller;

import com.ssafy.enjoytrip.user.model.dto.User;
import com.ssafy.enjoytrip.user.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rest/user")
@RestController
@CrossOrigin(origins = {"*"}, maxAge = 6000)
//@RequestMapping("/rest/user")
@Slf4j
public class UserRestController {
    private UserService userService;
    private static final String SUCCESS = "success";

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable String id) {
        log.debug("user.search.....id:", id);
        User user = userService.search(id);
        log.debug("user: ",user);
        if (user == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest user){
//
//    }
    @PostMapping
    public ResponseEntity<?> regist(@RequestBody User user){
        log.debug("user.regist.... user:",user);
        userService.regist(user);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user){
        log.debug("user.update.... user:",user);
        userService.update(user);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable String id){
        log.debug("user.remove.... id:",id);
        userService.remove(id);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }
}
