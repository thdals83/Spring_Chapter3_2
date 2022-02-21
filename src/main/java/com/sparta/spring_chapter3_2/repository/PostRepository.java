package com.sparta.spring_chapter3_2.repository;

import com.sparta.spring_chapter3_2.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
