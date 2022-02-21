package com.sparta.spring_chapter3_2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class PostRequestDTO {

    private String contents; //내용
    private String nickName;
    private String image; //내용
    private String type;

}
