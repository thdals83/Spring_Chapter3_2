package com.sparta.spring_chapter3_2.model;

import com.sparta.spring_chapter3_2.dto.LikeRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class LikeNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long userId;

    public LikeNumber(LikeRequestDTO likeRequestDTO){
        this.postId = likeRequestDTO.getPostId();
        this.userId = likeRequestDTO.getUserId();
    }

}