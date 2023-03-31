package com.example.silvertiger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter

public class BookMarkDto {
    private String context_id;
    private String name;
    private String url;

    public BookMarkDto(String context_id,String name,String url){
        this.context_id=context_id;
        this.name=name;
        this.url=url;
    }
}
