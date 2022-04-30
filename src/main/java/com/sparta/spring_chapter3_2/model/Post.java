package com.sparta.spring_chapter3_2.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.spring_chapter3_2.dto.PostRequestDTO;
import com.sparta.spring_chapter3_2.dto.PostUpdateRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@AllArgsConstructor
@Builder
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<LikeNumber> likeNumber = new ArrayList<>();

    public void update(PostUpdateRequestDTO updateRequestDTO) {
        this.contents = updateRequestDTO.getContents();
        this.nickName = updateRequestDTO.getNickName();
        this.likeCount = updateRequestDTO.getLikeCount();
        this.image = updateRequestDTO.getImage();
        this.type = updateRequestDTO.getType();
    }
}