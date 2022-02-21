package com.sparta.spring_chapter3_2.repository;


import com.sparta.spring_chapter3_2.model.LikeNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeNumberRepository extends JpaRepository<LikeNumber, Long> {
}
