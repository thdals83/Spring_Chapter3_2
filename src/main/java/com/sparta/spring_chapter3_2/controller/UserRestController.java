package com.sparta.spring_chapter3_2.controller;


import com.sparta.spring_chapter3_2.dto.UserLoginDTO;
import com.sparta.spring_chapter3_2.dto.UserReturnDTO;
import com.sparta.spring_chapter3_2.dto.UserRequestDTO;
import com.sparta.spring_chapter3_2.model.User;
import com.sparta.spring_chapter3_2.repository.UserRepository;
import com.sparta.spring_chapter3_2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class UserRestController {
    private final UserService userService;

    @PostMapping("/api/register") //유저 회원가입
    public UserReturnDTO createuser(@RequestBody UserRequestDTO requestDTO){
        //회원가입 확인
        return userService.checkRegister(requestDTO);
    }

    @PostMapping("/api/login") //유저 로그인
    public UserReturnDTO checklogin(@RequestBody UserLoginDTO loginDTO){
        //로그인 확인
        return userService.checklogin(loginDTO);
    }
}
