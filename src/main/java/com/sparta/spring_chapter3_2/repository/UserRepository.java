package com.sparta.spring_chapter3_2.repository;

import com.sparta.spring_chapter3_2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByuserId(String id);

    User findByuserId(String id);
}
