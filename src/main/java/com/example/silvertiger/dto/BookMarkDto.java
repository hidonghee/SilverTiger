package com.example.silvertiger.dto;

import lombok.Data;

import javax.persistence.Id;

@Data
public class BookMarkDto {
    private String context_id;
    private String name;
    private String url;
}
