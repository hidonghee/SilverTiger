package com.example.silvertiger.model;

import lombok.Data;

// Account 요청 객체
@Data
public class AccountRequest {
    private String id;
    private String passwd;
    private String name;
    private String gender;
    private String address;
    private String tel;
    private String city;
    private String date;
}
