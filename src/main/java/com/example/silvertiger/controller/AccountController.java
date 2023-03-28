package com.example.silvertiger.controller;

import com.example.silvertiger.dto.LoginDto;
import com.example.silvertiger.dto.JoinDto;
import com.example.silvertiger.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController //즉 주용도는 JSON/XML형태로 객체 데이터 반환을 목적으로 합니다.
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


// 회원가입 API
    @PostMapping("/join")
    public String join(@Valid @RequestBody JoinDto joinDto) {
        return accountService.join(joinDto);
    }
// 로그인 API
    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto){
        return accountService.login(loginDto);
    }
}

