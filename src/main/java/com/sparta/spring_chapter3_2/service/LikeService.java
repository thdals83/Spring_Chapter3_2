package com.sparta.spring_chapter3_2.service;

import com.sparta.spring_chapter3_2.dto.LikeRequestDTO;
import com.sparta.spring_chapter3_2.dto.PostUpdateRequestDTO;
import com.sparta.spring_chapter3_2.dto.UserReturnDTO;
import com.sparta.spring_chapter3_2.model.LikeNumber;
import com.sparta.spring_chapter3_2.model.Post;
import com.sparta.spring_chapter3_2.model.User;
import com.sparta.spring_chapter3_2.repository.LikeNumberRepository;
import com.sparta.spring_chapter3_2.repository.PostRepository;
import com.sparta.spring_chapter3_2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeNumberRepository likeNumberRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public UserReturnDTO pluslike(LikeRequestDTO likeRequestDTO){
        UserReturnDTO returnDTO = new UserReturnDTO();

        //이미 값이 있을 때
        int likecnt = likeNumberRepository.findByPostIdAndUserId(likeRequestDTO.getPostId(),likeRequestDTO.getUserId()).size();
        if (likecnt != 0){
            //like 디비에서 삭제
            List <LikeNumber> likeinfo = likeNumberRepository.findByPostIdAndUserId(likeRequestDTO.getPostId(),likeRequestDTO.getUserId());
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

        //좋아요 DB 저장
        Optional<User> user = userRepository.findById(likeRequestDTO.getUserId());
        Optional<Post> post2 = postRepository.findById(likeRequestDTO.getPostId());

        user.ifPresent(user1 -> {
            post2.ifPresent(post1 ->{
                LikeNumber likeNumber = new LikeNumber(post1,user1);
                likeNumberRepository.save(likeNumber);
            });
        });

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
}
