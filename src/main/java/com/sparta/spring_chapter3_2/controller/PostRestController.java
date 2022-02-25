package com.sparta.spring_chapter3_2.controller;


import com.sparta.spring_chapter3_2.dto.PostRemoveRequestDTO;
import com.sparta.spring_chapter3_2.dto.PostRequestDTO;
import com.sparta.spring_chapter3_2.dto.PostUpdateRequestDTO;
import com.sparta.spring_chapter3_2.dto.UserReturnDTO;
import com.sparta.spring_chapter3_2.model.Post;
import com.sparta.spring_chapter3_2.repository.PostRepository;
import com.sparta.spring_chapter3_2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostRestController {
    private final PostRepository postRepository;
    private final PostService postService;

    @PostMapping("/api/post") //게시물 등록
    public UserReturnDTO createpost(@RequestBody PostRequestDTO requestDTO) {
        UserReturnDTO returnDTO = new UserReturnDTO();

        Post post = new Post(requestDTO);
        postRepository.save(post);
        returnDTO.setResult(true);
        returnDTO.setMsg("success");

        return returnDTO;
    }

    //좋아요 다만들면 리턴 좋아요도 같이 해주기
    @GetMapping("/api/post") //게시물 조회
    public List<Post> getPost(){
        return postRepository.findAllByOrderByModifiedAtDesc();
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
