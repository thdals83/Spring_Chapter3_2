package com.sparta.spring_chapter3_2.controller;


import com.sparta.spring_chapter3_2.dto.LikeRequestDTO;
import com.sparta.spring_chapter3_2.dto.UserLoginDTO;
import com.sparta.spring_chapter3_2.dto.UserRequestDTO;
import com.sparta.spring_chapter3_2.dto.UserReturnDTO;
import com.sparta.spring_chapter3_2.repository.LikeNumberRepository;
import com.sparta.spring_chapter3_2.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class LikeRestController {
    private final LikeNumberRepository likeNumberRepository;
    private final LikeService likeService;

    //게시글 좋아요
    @PostMapping("/api/like")
    public UserReturnDTO clicklike(@RequestBody LikeRequestDTO likeRequestDTO, HttpServletRequest request){
        //세션 존재 확인
        HttpSession session = request.getSession();
        UserReturnDTO returnDTO = new UserReturnDTO();
        if (session.getAttribute("res") == null){
            returnDTO.setResult(false);
            returnDTO.setMsg("로그인을 해주세요");
            return returnDTO;
        }
        return likeService.pluslike(likeRequestDTO);
    }

    //어차피 로그인 해야 좋아요 보여서 세션 존재 확인 스킵
    @DeleteMapping("/api/like")
    public UserReturnDTO deletelike(@RequestBody LikeRequestDTO likeRequestDTO){
        UserReturnDTO returnDTO = new UserReturnDTO();


        return likeService.dellike(likeRequestDTO);
    }


}
