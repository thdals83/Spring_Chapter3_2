package com.sparta.spring_chapter3_2.model;


import com.sparta.spring_chapter3_2.dto.PostRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String username; //내용

    @Column(nullable = false)
    private String image; //내용

    public Post(PostRequestDTO requestDTO){
        this.contents = requestDTO.getContents();
        this.likeCount = 0;
        this.username = requestDTO.getUsername();
        this.image = requestDTO.getImage();
    }
    
}
