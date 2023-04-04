package com.example.silvertiger.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class BookMarkDto {
    private String contextId;
    private String name;
    private String url;

    public BookMarkDto(String contextId,String name,String url){
        this.contextId=contextId;
        this.name=name;
        this.url=url;
    }
}
