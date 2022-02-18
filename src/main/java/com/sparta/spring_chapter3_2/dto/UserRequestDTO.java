package com.sparta.spring_chapter3_2.dto;

import lombok.Getter;

import javax.persistence.Column;


@Getter
public class UserRequestDTO {

    private String userId; //이메일아이디
    private String password; //비밀번호
    private String userPwdCheck; //비밀번호
    private String nickName; //닉네임
}
