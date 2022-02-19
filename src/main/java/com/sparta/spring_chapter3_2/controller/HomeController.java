package com.sparta.spring_chapter3_2.controller;


import com.sparta.spring_chapter3_2.dto.UserReturnDTO;
import com.sparta.spring_chapter3_2.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @ResponseBody
    @GetMapping("/")
    public UserReturnDTO home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        UserReturnDTO res = new UserReturnDTO();
        res.setResult(true);
        res.setMsg(userDetails.getUsername());

        model.addAttribute("username", userDetails.getUsername());
        System.out.println("성공");
        return res;
    }
}
