package com.sparta.spring_chapter3_2.controller;


import com.sparta.spring_chapter3_2.dto.PostRequestDTO;
import com.sparta.spring_chapter3_2.dto.UserReturnDTO;
import com.sparta.spring_chapter3_2.model.Post;
import com.sparta.spring_chapter3_2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostRestController {
    private final PostRepository postRepository;

    @PostMapping("/api/post") //게시물 등록
    public UserReturnDTO createpost(@RequestBody PostRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserReturnDTO returnDTO = new UserReturnDTO();

        //세션 확인
        if (session.getAttribute("res") == null) {
            returnDTO.setResult(false);
            returnDTO.setMsg("로그인을 해주세요");
            return returnDTO;
        }
        Post post = new Post(requestDTO);
        postRepository.save(post);
        returnDTO.setResult(true);
        returnDTO.setMsg("success");

        return returnDTO;
    }

    @GetMapping("/api/post") //게시물 조회
    public List<Post> getPost(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

}
