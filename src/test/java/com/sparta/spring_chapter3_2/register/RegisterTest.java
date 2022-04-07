package com.sparta.spring_chapter3_2.register;

import com.sparta.spring_chapter3_2.dto.UserRequestDTO;
import com.sparta.spring_chapter3_2.dto.UserReturnDTO;
import com.sparta.spring_chapter3_2.repository.UserRepository;
import com.sparta.spring_chapter3_2.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RegisterTest {
    @Mock
    UserRepository userRepository;

    private String username; //이메일아이디
    private String password; //비밀번호
    private String userPwdCheck; //비밀번호
    private String nickName; //닉네임

    @Test
    @DisplayName("회원가입 - 성공시")
    void createRegister_success(){
        //given
        username = "1234@naver.com";
        password = "yu32i1uy32";
        userPwdCheck = "yu32i1uy32";
        nickName = "asaszxczc";

        UserRequestDTO requestDTO = new UserRequestDTO(username,password,userPwdCheck,nickName);
        UserService userService = new UserService(userRepository);
        UserReturnDTO returnDTO =userService.checkRegister(requestDTO);

        assertEquals(true,returnDTO.getResult());

    }
}