package com.sparta.spring_chapter3_2.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReturnDTO {
    private boolean result;
    private String msg;

    public boolean getResult(){
        this.result = result;
        return result;
    }
}

