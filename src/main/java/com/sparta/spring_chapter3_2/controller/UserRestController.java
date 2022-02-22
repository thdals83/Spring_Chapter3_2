package com.sparta.spring_chapter3_2.controller;


import com.sparta.spring_chapter3_2.dto.LoginReturnDTO;
import com.sparta.spring_chapter3_2.dto.UserLoginDTO;
import com.sparta.spring_chapter3_2.dto.UserReturnDTO;
import com.sparta.spring_chapter3_2.dto.UserRequestDTO;
import com.sparta.spring_chapter3_2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;


@RequiredArgsConstructor
@RestController
public class UserRestController {
    private final UserService userService;

    @PostMapping("/api/register") //유저 회원가입
    public UserReturnDTO createuser(@RequestBody UserRequestDTO requestDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserReturnDTO returnDTO = new UserReturnDTO();

        //세션 확인
        if (session.getAttribute("res") != null) {
            returnDTO.setResult(false);
            returnDTO.setMsg("이미 로그인 되어있습니다.");
            return returnDTO;
        }

        //회원가입 확인
        return userService.checkRegister(requestDTO);
    }

    @PostMapping("/api/login") //유저 로그인
    public LoginReturnDTO checklogin(@RequestBody UserLoginDTO loginDTO, HttpServletRequest request) throws IOException {
        //로그인 확인
        LoginReturnDTO res = userService.checklogin(loginDTO);
        HttpSession session = request.getSession();
        UserReturnDTO returnDTO = new UserReturnDTO();
        //세션 확인
        if (session.getAttribute("res") != null) {
            res.setNickName(null);
            res.setUsername(null);
            res.setResult(false);
            res.setMsg("이미 로그인 되어있습니다.");
            return res;
        }

        if (res.getResult()) {
            session.setAttribute("res", res);

        }
        return res;
    }

    @RestController
    public class GreetingController {
        @GetMapping("/hello")
        public String hello() {
            return "안녕하세요?";
        }
    }


}
