package com.example.silvertiger.service;

import com.example.silvertiger.DCO.Account;
import com.example.silvertiger.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {
    AccountRepository accountRepository;

    public Account create (Account member){
        return accountRepository.save(member);
    }

}
