package com.sparta.spring_chapter3_2.controller;


import com.sparta.spring_chapter3_2.dto.LikeRequestDTO;
import com.sparta.spring_chapter3_2.dto.UserReturnDTO;
import com.sparta.spring_chapter3_2.repository.LikeNumberRepository;
import com.sparta.spring_chapter3_2.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class LikeRestController {
    private final LikeNumberRepository likeNumberRepository;
    private final LikeService likeService;

    //게시글 좋아요
    @PostMapping("/api/like")
    public UserReturnDTO clicklike(@RequestBody LikeRequestDTO likeRequestDTO){
        //세션 존재 확인
        UserReturnDTO returnDTO = new UserReturnDTO();

        return likeService.pluslike(likeRequestDTO);
    }
}