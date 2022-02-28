package com.sparta.spring_chapter3_2.repository;

import com.sparta.spring_chapter3_2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByusername(String id);
    User findByUsername(String id);
}
