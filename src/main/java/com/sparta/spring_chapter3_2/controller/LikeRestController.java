package com.sparta.spring_chapter3_2.controller;


import com.sparta.spring_chapter3_2.dto.UserLoginDTO;
import com.sparta.spring_chapter3_2.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController

public class LikeRestController {
    @PostMapping("/api/like")
    // 들어가는 것들 바꾸기
    public String clicklike(@RequestBody UserLoginDTO loginDTO, HttpServletRequest request){
        HttpSession session = request.getSession();
//        System.out.println(session);
        if (session.getAttribute("res") == null){
            return "fail";
        }

        // 여기 바꾸기
        return "succes";
    }

}
