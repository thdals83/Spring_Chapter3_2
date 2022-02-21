package com.sparta.spring_chapter3_2.service;

import com.sparta.spring_chapter3_2.dto.LikeRequestDTO;
import com.sparta.spring_chapter3_2.dto.PostUpdateRequestDTO;
import com.sparta.spring_chapter3_2.dto.UserReturnDTO;
import com.sparta.spring_chapter3_2.model.LikeNumber;
import com.sparta.spring_chapter3_2.model.Post;
import com.sparta.spring_chapter3_2.repository.LikeNumberRepository;
import com.sparta.spring_chapter3_2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeNumberRepository likeNumberRepository;
    private final PostRepository postRepository;

    @Transactional
    public UserReturnDTO pluslike(LikeRequestDTO likeRequestDTO){
        UserReturnDTO returnDTO = new UserReturnDTO();

        //이미 값이 있을 때
        int likecnt = likeNumberRepository.findByPostIdAndUserId(likeRequestDTO.getPostId(),likeRequestDTO.getUserId()).size();
        if (likecnt != 0){
            returnDTO.setResult(false);
            returnDTO.setMsg("이미 존재합니다.");
            return returnDTO;
        }
        //좋아요 DB 저장
        LikeNumber likeNumber = new LikeNumber(likeRequestDTO);
        likeNumberRepository.save(likeNumber);

        //해당 POST 게시물 likecount +1 해주기
        Post post = postRepository.findById(likeRequestDTO.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

        PostUpdateRequestDTO updateRequestDTO = new PostUpdateRequestDTO(post);
        updateRequestDTO.setLikeCount(updateRequestDTO.getLikeCount() + 1);

        post.update(updateRequestDTO);

        //리턴

        returnDTO.setResult(true);
        returnDTO.setMsg("성공");
        return returnDTO;
    }

    @Transactional
    public UserReturnDTO dellike(LikeRequestDTO likeRequestDTO) {
        UserReturnDTO returnDTO = new UserReturnDTO();

        //값이 존재하지 않을 때 (생길 일은 없음)
        int likecnt = likeNumberRepository.findByPostIdAndUserId(likeRequestDTO.getPostId(),likeRequestDTO.getUserId()).size();
        if (likecnt == 0){
            returnDTO.setResult(false);
            returnDTO.setMsg("값이 존재하지 않습니다..");
            return returnDTO;
        }

        //like 디비에서 삭제
        List <LikeNumber> likeinfo = likeNumberRepository.findByPostIdAndUserId(likeRequestDTO.getPostId(),likeRequestDTO.getUserId());
        System.out.println(likeinfo.get(0).getId());
        likeNumberRepository.deleteById(likeinfo.get(0).getId());

        //해당 POST 게시물 likecount  - 1 해주기
        Post post = postRepository.findById(likeRequestDTO.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

        PostUpdateRequestDTO updateRequestDTO = new PostUpdateRequestDTO(post);
        updateRequestDTO.setLikeCount(updateRequestDTO.getLikeCount() - 1);

        post.update(updateRequestDTO);


        returnDTO.setResult(true);
        returnDTO.setMsg("성공");
        return returnDTO;
    }
}
