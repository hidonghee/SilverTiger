package com.example.silvertiger.service;

import com.example.silvertiger.dto.TokenDto;
import com.example.silvertiger.jwt.JwtTokenProvider;
import com.example.silvertiger.entity.Account;
import com.example.silvertiger.dto.LoginDto;
import com.example.silvertiger.dto.JoinDto;
import com.example.silvertiger.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@RequiredArgsConstructor
@Service

public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public Account join(JoinDto joinDto) {
        Account account = Account.builder()
                .id(joinDto.getId())
                .passwd(passwordEncoder.encode(joinDto.getPasswd()))  //비밀번호 인코딩
                .email(joinDto.getEmail())
                .name(joinDto.getName())
                .tel(joinDto.getTel())
                .date(joinDto.getDate())
                .roles(Collections.singletonList("ROLE_USER"))         //roles는 최초 USER로 설정
                .build();

        return accountRepository.save(account);
    }

    @Transactional
    public TokenDto login(LoginDto loginDto) {
        Account account = accountRepository.findById(loginDto.getId()).orElseThrow(
                () -> new IllegalArgumentException("가입되지 않은 ID 입니다.")
        );
        if (!passwordEncoder.matches(loginDto.getPassword(), account.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다");
        }
        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(jwtTokenProvider.createToken(account.getUsername(), account.getRoles()));
        return tokenDto;
    }
}

