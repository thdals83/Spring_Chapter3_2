package com.sparta.spring_chapter3_2.model;


import com.sparta.spring_chapter3_2.dto.PostRequestDTO;
import com.sparta.spring_chapter3_2.dto.PostUpdateRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String contents; //내용

    @Column(nullable = false)
    private int likeCount; //비밀번호

    @Column(nullable = false)
    private String nickName; //내용

    @Column(nullable = false)
    private String image; //내용

    @Column(nullable = false)
    private String type; //내용


    public Post(PostRequestDTO requestDTO){
        this.contents = requestDTO.getContents();
        this.likeCount = 0;
        this.nickName = requestDTO.getNickName();
        this.image = requestDTO.getImage();
        this.type = requestDTO.getType();
    }

    public void update(PostUpdateRequestDTO updateRequestDTO) {
        this.contents = updateRequestDTO.getContents();
        this.nickName = updateRequestDTO.getNickName();
        this.likeCount = updateRequestDTO.getLikeCount();
        this.image = updateRequestDTO.getImage();
        this.type = updateRequestDTO.getType();
    }
}
