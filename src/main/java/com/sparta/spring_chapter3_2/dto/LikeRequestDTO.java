package com.sparta.spring_chapter3_2.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeRequestDTO {
    private Long postId;
    private Long userId;
}
