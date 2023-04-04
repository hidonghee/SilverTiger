package com.example.silvertiger.dto;

import lombok.Data;

// Account 요청 객체
@Data
public class JoinDto {

    private String id;
    private String passwd;
    private String name;
    private String email;
    private String tel;
    private String date;
}
