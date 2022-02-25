package com.sparta.spring_chapter3_2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String username; //이메일아이디
    private String password; //비밀번호
    private String userPwdCheck; //비밀번호
    private String nickName; //닉네임
}