package com.sparta.spring_chapter3_2.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class LikeRequestDTO {
    private Long postId;
    private Long userId;
}
