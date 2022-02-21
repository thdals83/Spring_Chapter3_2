package com.sparta.spring_chapter3_2.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequestDTO {
    private Long postId;
    private String contents; //내용
    private String nickName;
    private int likeCount;
    private String image; //내용
    private String type;
}
