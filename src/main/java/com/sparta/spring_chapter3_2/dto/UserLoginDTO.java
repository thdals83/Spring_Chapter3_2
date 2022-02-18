package com.sparta.spring_chapter3_2.dto;

import lombok.Getter;

@Getter
public class UserLoginDTO {
    private String userId; //이메일아이디
    private String password; //비밀번호
}
