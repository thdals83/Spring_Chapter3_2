package com.sparta.spring_chapter3_2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginReturnDTO {
    private boolean result;
    private String msg;
    private String tokenname;

    public boolean getResult(){
        return result;
    }
}

