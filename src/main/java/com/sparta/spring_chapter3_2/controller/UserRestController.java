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

    @PostMapping("/api/login")  // 로그인
    public LoginReturnDTO login(@RequestBody UserLoginDTO loginDTO) {
        LoginReturnDTO returnDTO = new LoginReturnDTO();

        User user = userRepository.findByUsername(loginDTO.getUsername());

        //아이디가 존재하지 않을 때
        if (!userRepository.existsByusername(loginDTO.getUsername())){
            returnDTO.setResult(false);
            returnDTO.setMsg("아이디가 존재하지 않습니다.");
            returnDTO.setTokenname(null);
            return returnDTO;
        }

        //로그인 정보 틀렸을 때
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            returnDTO.setResult(false);
            returnDTO.setMsg("잘못된 비밀번호 입니다.");
            returnDTO.setTokenname(null);
            return returnDTO;
        }
        returnDTO.setResult(true);
        returnDTO.setMsg("로그인 성공");
        returnDTO.setTokenname(jwtTokenProvider.createToken(user.getUsername(), user.getRoles()));
        return returnDTO;
    }
}
