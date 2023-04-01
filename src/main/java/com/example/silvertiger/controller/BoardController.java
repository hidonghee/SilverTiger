package com.example.silvertiger.controller;

import com.example.silvertiger.dto.BoardDto;
import com.example.silvertiger.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
//경로가 반복될 경우 대표 주소를 작성해서 하위 매서드에서 반복적으로 작성하지 않아도 된다
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_USER','ADMIN')")
    public ResponseEntity<BoardDto> save(@RequestBody BoardDto boardDto, HttpServletRequest httpServletRequest){
        BoardDto saveBoardDto = boardService.save(httpServletRequest, boardDto);
        return ResponseEntity.ok(saveBoardDto);
    }

    @PutMapping("/update/{account_id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ADMIN')")
    public ResponseEntity<BoardDto> update(@RequestBody BoardDto boardDto, HttpServletRequest httpServletRequest) {
        BoardDto updatedBoardDto = boardService.update(httpServletRequest, boardDto);
        return ResponseEntity.ok(updatedBoardDto);
    }

    //게시글 삭제
    @DeleteMapping ("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ADMIN')")
    public ResponseEntity<BoardDto> delete(@RequestBody BoardDto boardDto) {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        BoardDto deleteBoardDto = boardService.delete(httpServletRequest, boardDto);
        return ResponseEntity.ok(deleteBoardDto);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_USER','ADMIN')")
    public ResponseEntity<List<BoardDto>> findAll() {
        List<BoardDto> boardDtoList = boardService.findAll();
        return new ResponseEntity<>(boardDtoList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ADMIN')")
    public ResponseEntity<BoardDto> findById(@PathVariable Long id) {
        boardService.updateHits(id);
        BoardDto boardDto = boardService.findById(id);
        return ResponseEntity.ok(boardDto);
    }

}
