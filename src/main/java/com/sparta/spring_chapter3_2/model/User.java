package com.sparta.spring_chapter3_2.model;


import com.sparta.spring_chapter3_2.dto.UserLoginDTO;
import com.sparta.spring_chapter3_2.dto.UserRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userId; //이메일아이디

    @Column(nullable = false)
    private String password; //비밀번호

    @Column(nullable = false)
    private String nickName; //닉네임

    public User(UserRequestDTO requestDTO) {
        this.userId = requestDTO.getUserId();
        this.password = requestDTO.getPassword();
        this.nickName = requestDTO.getNickName();
    }

    public User(UserLoginDTO loginDTO){
        this.userId = loginDTO.getUserId();
        this.password = loginDTO.getPassword();
    }



}
