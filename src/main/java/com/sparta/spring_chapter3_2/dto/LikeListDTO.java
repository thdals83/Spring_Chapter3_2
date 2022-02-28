package com.sparta.spring_chapter3_2.dto;


import com.sparta.spring_chapter3_2.model.LikeNumber;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class LikeListDTO {
    private Long id;
    private Long postId;
    private Long userId;

    public LikeListDTO(LikeNumber likeNumber){
        this.id = likeNumber.getId();
        this.postId = likeNumber.getPostId();
        this.userId = likeNumber.getUser().getId();
    }

}
