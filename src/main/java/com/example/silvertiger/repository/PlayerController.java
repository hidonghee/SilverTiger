package com.example.silvertiger.repository;

import com.example.silvertiger.DCO.Account;
import com.example.silvertiger.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //즉 주용도는 JSON/XML형태로 객체 데이터 반환을 목적으로 합니다.
@RequestMapping("/member")
class PlayerController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<Account> MemberCreate(Account account){
        return new ResponseEntity<Account>(accountService.create(account), HttpStatus.OK);
    }
}

