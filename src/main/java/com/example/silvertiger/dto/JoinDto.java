package com.example.silvertiger.dto;

import lombok.Builder;
import lombok.Data;

// Account 요청 객체
@Builder
@Data
public class JoinDto {

    private String id;
    private String passwd;
    private String name;
    private String email;
    private String tel;
    private String date;
}
