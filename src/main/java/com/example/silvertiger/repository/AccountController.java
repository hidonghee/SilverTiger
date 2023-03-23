package com.example.silvertiger.repository;

import com.example.silvertiger.model.Account;
import com.example.silvertiger.model.AccountRequest;
import com.example.silvertiger.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //즉 주용도는 JSON/XML형태로 객체 데이터 반환을 목적으로 합니다.
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/singup")
    public ResponseEntity<Account> AccountCreate(@RequestBody AccountRequest accountRequest){
        return new ResponseEntity<Account>(accountService.create(accountRequest), HttpStatus.OK);
    }
}

