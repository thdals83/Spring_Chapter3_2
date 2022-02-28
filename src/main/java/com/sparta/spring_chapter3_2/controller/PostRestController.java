package com.sparta.spring_chapter3_2.controller;


import com.sparta.spring_chapter3_2.dto.*;
import com.sparta.spring_chapter3_2.model.LikeNumber;
import com.sparta.spring_chapter3_2.model.Post;
import com.sparta.spring_chapter3_2.repository.LikeNumberRepository;
import com.sparta.spring_chapter3_2.repository.PostRepository;
import com.sparta.spring_chapter3_2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostRestController {
    private final PostRepository postRepository;
    private final PostService postService;
    private final LikeNumberRepository likeNumberRepository;

    @PostMapping("/api/post") //게시물 등록
    public UserReturnDTO createpost(@RequestBody PostRequestDTO requestDTO) {
        UserReturnDTO returnDTO = new UserReturnDTO();

        Post post = new Post(requestDTO);
        postRepository.save(post);
        returnDTO.setResult(true);
        returnDTO.setMsg("success");

        return returnDTO;
    }

    //좋아요 다만들면 리턴 좋아요도 같이 해주기 원래대로면 Repository 들어가는건 서비스에서 해야 하지만 시간 없음
    @PostMapping("/api/showpost") //게시물 조회
    public LoginGetReturnDTO getPost(@RequestBody PostgetDTO postgetDTO){
        //아이디 없을 때
        if (postgetDTO.getUserId() == null){
            List<LikeListDTO> likeListDTO = null;
            LoginGetReturnDTO loginGetReturnDTO = new LoginGetReturnDTO(postRepository.findAllByOrderByModifiedAtDesc(), likeListDTO);
            return loginGetReturnDTO;
        }
        //userid 넣는 작업
        List<LikeNumber> liketable = likeNumberRepository.findByUserId(postgetDTO.getUserId());
        List<LikeListDTO> likeListDTO = new ArrayList<LikeListDTO>();

        for (int i =0; i < liketable.size(); i++){
            LikeListDTO likeListDTOtmp = new LikeListDTO(liketable.get(i));
            likeListDTO.add(likeListDTOtmp);
        }

        //josn 총 입력 및 리턴턴
       LoginGetReturnDTO loginGetReturnDTO = new LoginGetReturnDTO(postRepository.findAllByOrderByModifiedAtDesc(), likeListDTO);
        return loginGetReturnDTO;
    }

    //게시물 수정
    @PutMapping("/api/post") //게시물 수정
    public UserReturnDTO putPost(@RequestBody PostUpdateRequestDTO updateRequestDTO){
        return postService.update(updateRequestDTO);
    }

    //게시물 삭제
    @DeleteMapping("/api/post") // 게시물 삭제
    public UserReturnDTO deletePost(@RequestBody PostRemoveRequestDTO removeRequestDTO){
        UserReturnDTO returnDTO = new UserReturnDTO();
        postRepository.deleteById(removeRequestDTO.getPostId());
        returnDTO.setResult(true);
        returnDTO.setMsg("success");
        return returnDTO;
    }

}
