package com.sparta.spring_chapter3_2.service;


import com.sparta.spring_chapter3_2.dto.PostRequestDTO;
import com.sparta.spring_chapter3_2.dto.PostUpdateRequestDTO;
import com.sparta.spring_chapter3_2.dto.UserReturnDTO;
import com.sparta.spring_chapter3_2.model.Post;
import com.sparta.spring_chapter3_2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public UserReturnDTO update(PostUpdateRequestDTO updateRequestDTO){
        UserReturnDTO res = new UserReturnDTO();
        Post post = postRepository.findById(updateRequestDTO.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

        post.update(updateRequestDTO);

        res.setResult(true);
        res.setMsg("success");
        return res;

    }

}
