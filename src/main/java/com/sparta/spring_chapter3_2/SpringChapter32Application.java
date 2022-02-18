package com.sparta.spring_chapter3_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 시간 자동 변경 가능하게
@SpringBootApplication
public class SpringChapter32Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringChapter32Application.class, args);

    }

}
