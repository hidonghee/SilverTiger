package com.example.silvertiger.service;

import com.example.silvertiger.dto.BookMarkDto;
import com.example.silvertiger.dto.JoinDto;
import com.example.silvertiger.entity.Account;
import com.example.silvertiger.entity.AccountBookMark;
import com.example.silvertiger.entity.BookMark;
import com.example.silvertiger.jwt.JwtAuthenticationFilter;
import com.example.silvertiger.jwt.JwtTokenProvider;
import com.example.silvertiger.repository.AccountBookMarkRepository;
import com.example.silvertiger.repository.AccountRepository;
import com.example.silvertiger.repository.BookMarkRepository;
import com.example.silvertiger.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookMarkService {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final AccountRepository accountRepository;
    private final BookMarkRepository bookMarkRepository;
    private final AccountBookMarkRepository accountBookMarkRepository;
    private final JwtTokenProvider jwtTokenProvider;

//    private final AuthenticationPrincipal authenticationPrincipal
    @Transactional
    public AccountBookMark mark(BookMarkDto bookMarkDTo , HttpServletRequest httpServletRequest){
        String id = getUser(httpServletRequest);
        bookMarkRepository.save(BookMark.builder().contentId(bookMarkDTo.getContext_id()).build());
        return accountBookMarkRepository.save(AccountBookMark.builder().accountId(id).bookMarkId(bookMarkDTo.getContext_id()).build());
    }

    private String getUser(HttpServletRequest httpServletRequest) {
        String jwt = jwtTokenProvider.resolveToken(httpServletRequest);
        return jwtTokenProvider.getUserPk(jwt);
    }

}
