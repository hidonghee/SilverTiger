package com.example.silvertiger.controller;

import com.example.silvertiger.dto.BookMarkDto;
import com.example.silvertiger.entity.Account;
import com.example.silvertiger.entity.AccountBookMark;
import com.example.silvertiger.entity.BookMark;
import com.example.silvertiger.service.BookMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController //즉 주용도는 JSON/XML형태로 객체 데이터 반환을 목적으로 합니다.
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookMarkController {

    private final BookMarkService bookMarkService;

    @PostMapping("/mark")
    @PreAuthorize("hasAnyRole('ROLE_USER','ADMIN')")
    public ResponseEntity <?> mark(@RequestBody BookMarkDto bookMarkDto, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(bookMarkService.on(bookMarkDto,httpServletRequest));
    }

    @PostMapping("/test")
    @Secured("ROLE_USER")
    public String test() {
        return "allow";
    }


}
