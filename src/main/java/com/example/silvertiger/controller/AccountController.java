package com.example.silvertiger.controller;

import com.example.silvertiger.dto.JoinDto;
import com.example.silvertiger.dto.LoginDto;
import com.example.silvertiger.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController //즉 주용도는 JSON/XML형태로 객체 데이터 반환을 목적으로 합니다.
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


//    @PostMapping("/singup")
//    public ResponseEntity<Account> AccountCreate(@RequestBody AccountRequest accountRequest){
//        return new ResponseEntity<Account>(accountService.create(accountRequest), HttpStatus.OK);
//    }

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

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String getMyUserInfo() {
        return "test";
    }
}

