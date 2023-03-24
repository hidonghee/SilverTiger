package com.example.silvertiger.service;

import com.example.silvertiger.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomAccountDetailSerivce implements UserDetailsService {
    private final AccountRepository accountRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("사용자 x "));
    }
}
