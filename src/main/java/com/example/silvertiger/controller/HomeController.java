package com.example.silvertiger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //기본 주소가 호출이 되면 index.html을 찾아간다.
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
