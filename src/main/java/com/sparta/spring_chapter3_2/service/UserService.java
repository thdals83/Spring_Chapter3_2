package com.sparta.spring_chapter3_2.service;


import com.sparta.spring_chapter3_2.dto.UserRequestDTO;
import com.sparta.spring_chapter3_2.dto.UserReturnDTO;
import com.sparta.spring_chapter3_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.regex.Pattern;

//@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //회원가입 확인
    @Transactional
    public UserReturnDTO checkRegister(UserRequestDTO requestDTO) {

        UserReturnDTO res = new UserReturnDTO();
        String userid = requestDTO.getUsername();
        String password = requestDTO.getPassword();
        String passwordCheck = requestDTO.getPasswordCheck();
        String nickName = requestDTO.getNickName();

        //비밀번호 일치하지 않을 때
        if (!Objects.equals(password, passwordCheck)){
            res.setResult(false);
            res.setMsg("비밀번호가 일치하지 않습니다.");
            return res;
        }
        //닉네임 틀렸을 때
        if (password.contains(nickName) || password.length() < 4){
            res.setResult(false);
            res.setMsg("패스워드가 4글자 이하거나, 패스워드에 닉네임이 포함되어있습니다.");
            return res;
        }
        // 닉네임이 알맞지 않을 때
        if (nickName.length() < 3 ||  !Pattern.matches("^[a-zA-Z0-9]*$",nickName)){
            res.setResult(false);
            res.setMsg("닉네임이 3글자 이하거나, 형식이 올바르지 않습니다.");
            return res;
        }

        res.setResult(true);
        res.setMsg("회원가입 성공");
        return res;

    }

}


