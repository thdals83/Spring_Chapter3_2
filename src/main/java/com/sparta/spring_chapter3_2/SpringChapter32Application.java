package com.sparta.spring_chapter3_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing // 시간 자동 변경 가능하게
@SpringBootApplication
public class SpringChapter32Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringChapter32Application.class, args);
    }

    //CORS 차단 풀어주는거 나중에 주소 변경하기
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*")
                        .allowedMethods("GET","POST","DELETE","PUT");
            }
        };
    }
}