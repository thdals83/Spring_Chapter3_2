package com.sparta.spring_chapter3_2.dto;

import com.sparta.spring_chapter3_2.model.LikeNumber;
import com.sparta.spring_chapter3_2.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginGetReturnDTO {
    private List<Post> total;
    private List<LikeNumber> myLike;

//    LoginGetReturnDTO(List<Post> total, List<LikeNumber> myLike){
//        this.total = total;
//        this.myLike = myLike;
//    }

}
