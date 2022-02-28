package com.sparta.spring_chapter3_2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class LikeNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "USER_ID")
    private User user;

    public LikeNumber(Long postId, User user) {
        this.postId = postId;
        this.user = user;
    }

//    public LikeNumber(LikeRequestDTO likeRequestDTO){
//        this.postId = likeRequestDTO.getPostId();
//        this.user = likeRequestDTO.getUser();
//    }

}