package com.example.silvertiger.dto;

import lombok.Getter;
import lombok.Setter;


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
