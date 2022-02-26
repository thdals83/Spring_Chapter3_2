package com.sparta.spring_chapter3_2.dto;


import com.sparta.spring_chapter3_2.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateRequestDTO {
    private Long postId;
    private String contents; //내용
    private String nickName;
    private int likeCount;
    private String image; //내용
    private String type;

    public PostUpdateRequestDTO(Post post){
        this.postId = post.getId();
        this.contents = post.getContents();
        this.nickName = post.getNickName();
        this.likeCount = post.getLikeCount();
        this.image = post.getImage();
        this.type = post.getType();

    }
}
