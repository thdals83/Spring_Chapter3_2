package com.sparta.spring_chapter3_2.controller;


import com.sparta.spring_chapter3_2.dto.LoginReturnDTO;
import com.sparta.spring_chapter3_2.dto.UserLoginDTO;
import com.sparta.spring_chapter3_2.dto.UserReturnDTO;
import com.sparta.spring_chapter3_2.dto.UserRequestDTO;
import com.sparta.spring_chapter3_2.model.User;
import com.sparta.spring_chapter3_2.repository.UserRepository;
import com.sparta.spring_chapter3_2.security.jwt.JwtTokenProvider;
import com.sparta.spring_chapter3_2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;


@RequiredArgsConstructor
@RestController
public class UserRestController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @PostMapping("/api/register") //유저 회원가입
    public UserReturnDTO createuser(@RequestBody UserRequestDTO requestDTO) {
        //회원가입
        UserReturnDTO returnDTO = userService.checkRegister(requestDTO);

        if (returnDTO.getResult()){
            userRepository.save(User.builder()
                    .username(requestDTO.getUsername())
                    .nickName(requestDTO.getNickName())
                    .password(passwordEncoder.encode(requestDTO.getPassword()))
                    .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                    .build()).getId();
        }

        return returnDTO;
    }
    ////////////////////////////////////////////////////////////////////////////////
    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        User member = userRepository.findByUsername(user.get("username"));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        System.out.println(jwtTokenProvider.createToken(member.getUsername(), member.getRoles()).getClass().getName());
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }


    @PostMapping("/api/login") //유저 로그인
    public LoginReturnDTO checklogin(@RequestBody UserLoginDTO loginDTO){
        //로그인 확인
        LoginReturnDTO res = userService.checklogin(loginDTO);
        UserReturnDTO returnDTO = new UserReturnDTO();

        return res;
    }
}
