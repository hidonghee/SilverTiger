package com.example.silvertiger.service;

import com.example.silvertiger.dto.BookMarkDto;
import com.example.silvertiger.entity.AccountBookMark;
import com.example.silvertiger.entity.BookMark;
import com.example.silvertiger.jwt.JwtTokenProvider;
import com.example.silvertiger.repository.AccountBookMarkRepository;
import com.example.silvertiger.repository.AccountRepository;
import com.example.silvertiger.repository.BookMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BookMarkService {

    private final AccountRepository accountRepository;
    private final BookMarkRepository bookMarkRepository;
    private final AccountBookMarkRepository accountBookMarkRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public AccountBookMark on(BookMarkDto bookMarkDTo , HttpServletRequest httpServletRequest){
        String id = getUser(httpServletRequest);
        bookMarkRepository.save(BookMark.builder().contentId(bookMarkDTo.getContext_id()).build());
        return accountBookMarkRepository.save(AccountBookMark.builder().accountId(id).bookMarkId(bookMarkDTo.getContext_id()).build());
    }
//     //북마크 지우기 추가하기
//    @Transactional
//    public AccountBookMark off(BookMarkDto bookMarkDto, HttpServletRequest httpServletRequest){
//        String id = getUser(httpServletRequest);
//        bookMarkRepository.delete();
//    }






    private String getUser(HttpServletRequest httpServletRequest) {
        String jwt = jwtTokenProvider.resolveToken(httpServletRequest);
        return jwtTokenProvider.getUserPk(jwt);
    }

}
