package com.sparta.spring_chapter3_2.repository;


import com.sparta.spring_chapter3_2.model.LikeNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeNumberRepository extends JpaRepository<LikeNumber, Long> {
    List<LikeNumber> findByPostIdAndUserId(Long postId, Long userId);

}
