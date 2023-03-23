package com.example.silvertiger.service;

import com.example.silvertiger.model.Account;
import com.example.silvertiger.model.AccountRequest;
import com.example.silvertiger.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public Account create (AccountRequest accountRequest){
        Account account = new Account();
        BeanUtils.copyProperties(accountRequest,account);
        return accountRepository.save(account);
    }

}
