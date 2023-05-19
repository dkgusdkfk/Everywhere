package com.ssafy.enjoytrip.user.controller;

import com.ssafy.enjoytrip.board.model.dto.PageBean;
import com.ssafy.enjoytrip.trip.model.dto.AddressResponse;
import com.ssafy.enjoytrip.trip.model.dto.Sido;
import com.ssafy.enjoytrip.trip.model.service.TripService;
import com.ssafy.enjoytrip.user.model.dto.FindPasswordRequest;
import com.ssafy.enjoytrip.user.model.dto.LoginRequest;
import com.ssafy.enjoytrip.user.model.dto.User;
import com.ssafy.enjoytrip.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final TripService tripService;

    @ExceptionHandler(Exception.class)
    public String handler(Exception ex, Model model) {
        model.addAttribute("msg", ex.getMessage());
        return "/user/LoginFail";
    }

    //login
    @PostMapping("/login")
    public String login(LoginRequest request, HttpSession session) {
        User user = userService.login(request);
        session.setAttribute("userInfo", user);
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        log.debug("User....................logout");
        session.invalidate();
        return "redirect:/index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        List<Sido> list = tripService.getSidoList();
        model.addAttribute("sidoList",list);
        return "user/SignUp";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.register(user);
        return "redirect:/index";
    }

    @GetMapping("/detail")
    public String search(@RequestParam("id") String id, Model model) {
        User user = userService.search(id);
        if (user.getAddress1() != null) {
            AddressResponse address = tripService.getAddress(Integer.parseInt(user.getAddress1()), Integer.parseInt(user.getAddress2()));
            model.addAttribute("address", address);
        }
        model.addAttribute("userInfo", user);
        return "user/MyPage";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") String id, Model model, PageBean bean) {
        User user = userService.search(id);
        List<Sido> list = tripService.getSidoList();

        model.addAttribute("userInfo", user);
        model.addAttribute("sidoList",list);
        return "user/UserInfoModify";
    }

    @PostMapping("/update")
    public String update(User user, Model model) {
        userService.update(user);
        model.addAttribute("id", user.getId());
        return "redirect:/user/detail?id=" + user.getId();
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("id") String id, HttpSession session) {
        userService.remove(id);
        session.invalidate();
        return "redirect:/index";
    }

    @GetMapping("/findPassword")
    public String findPassword() {
        return "user/findPwForm";
    }

    @PostMapping("/findPassword")
    public String findPassword(FindPasswordRequest request, Model model) {
        User user = userService.findPassword(request);
        model.addAttribute("findUser", user);
        return "user/findPwForm";
    }

    @ResponseBody
    @GetMapping("/idcheck")
    public int idCheck(@RequestParam String userId) {
        return userService.idCheck(userId);
    }

}
