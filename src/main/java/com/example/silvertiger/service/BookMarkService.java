package com.example.silvertiger.service;

import com.example.silvertiger.dto.BookMarkDto;
import com.example.silvertiger.entity.Account;
import com.example.silvertiger.entity.AccountBookMarkPk;
import com.example.silvertiger.entity.BookMark;
import com.example.silvertiger.jwt.JwtTokenProvider;
import com.example.silvertiger.repository.AccountRepository;
import com.example.silvertiger.repository.BookMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookMarkService {

    private final AccountRepository accountRepository;
    private final BookMarkRepository bookMarkRepository;
    private final JwtTokenProvider jwtTokenProvider;


    // 북마크 생성
    @Transactional
    public BookMarkDto on(BookMarkDto bookMarkDTo , HttpServletRequest httpServletRequest) {
        String id = getUser(httpServletRequest);
        Account account = accountRepository.findById(id).get();

        BookMark bookMark = BookMark.builder()
                .contextId(bookMarkDTo.getContext_id())
                .name(bookMarkDTo.getName())
                .url(bookMarkDTo.getUrl())
                .build();

        account.addBookMark(bookMark);
        accountRepository.save(account);
        return bookMarkDTo;
    }
     //북마크 지우기
    @Transactional
    public BookMarkDto off(BookMarkDto bookMarkDto, HttpServletRequest httpServletRequest){
        String id = getUser(httpServletRequest);
        Account account = accountRepository.findById(id).get();
        AccountBookMarkPk accountBookMarkPk = new AccountBookMarkPk(account,bookMarkDto.getContext_id());
        BookMark bookMark = bookMarkRepository.findById(accountBookMarkPk).get();
        account.removeBookMark(bookMark);
        accountRepository.save(account);
        return bookMarkDto;
    }

    // 북마크 조회(연관 테이블 join X)
    @Transactional
    public List<BookMarkDto> list(HttpServletRequest httpServletRequest){
        String id = getUser(httpServletRequest);
        Account account = accountRepository.findById(id).get();
        List <BookMarkDto> bookMarkDtos = bookMarkRepository.findAllBookMarks(account);
        return bookMarkDtos;
    }


    // Token으로 부터 id 추출.
    private String getUser(HttpServletRequest httpServletRequest) {
        String jwt = jwtTokenProvider.resolveToken(httpServletRequest);
        return jwtTokenProvider.getUserPk(jwt);
    }

}
