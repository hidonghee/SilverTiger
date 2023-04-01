package com.example.silvertiger.service;

import com.example.silvertiger.dto.BoardDto;
import com.example.silvertiger.entity.Account;
import com.example.silvertiger.entity.BoardEntity;
import com.example.silvertiger.jwt.JwtTokenProvider;
import com.example.silvertiger.repository.AccountRepository;
import com.example.silvertiger.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class BoardService {
    private final BoardRepository boardRepository;
    private final AccountRepository accountRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private String getUser(HttpServletRequest httpServletRequest) {
        String jwt = jwtTokenProvider.resolveToken(httpServletRequest);
        return jwtTokenProvider.getUserPk(jwt);
    }

    //게시판 저장
    @Transactional
    public BoardDto save(HttpServletRequest httpServletRequest, BoardDto boardDto) {
        String id = getUser(httpServletRequest); // get username from JWT
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        boardDto.setAccount(account); // set account in DTO
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDto, account);
        boardRepository.save(boardEntity);
        return BoardDto.toBoardDto(boardEntity);
    }


    @Transactional
    public BoardDto update(HttpServletRequest httpServletRequest, BoardDto boardDto) {
        String id = getUser(httpServletRequest); // get username from JWT
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        boardDto.setAccount(account); // set account in DTO
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDto, account);
        boardRepository.save(boardEntity);
        return BoardDto.toBoardDto(boardEntity);
    }

    //게시글 삭제
    public BoardDto delete(HttpServletRequest httpServletRequest, BoardDto boardDto) {
        String id = getUser(httpServletRequest); // get username from JWT
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        boardDto.setAccount(account); // set account in DTO
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDto, account);
        boardRepository.delete(boardEntity);
        return BoardDto.toBoardDto(boardEntity);
    }

    @Transactional
    //게시판 전체 조회
    public List<BoardDto> findAll() {
        return boardRepository.findAllBoardDto();
    }
/*    public List<BoardDto> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (BoardEntity boardEntity: boardEntityList){
            boardDtoList.add(BoardDto.toBoardDto(boardEntity));
        }
        return boardDtoList;
    }*/

    @Transactional
    public void updateHits(Long id){
        boardRepository.updateHits(id);
    }

    @Transactional
    public BoardDto findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDto boardDto = BoardDto.toBoardDto(boardEntity);
            return boardDto;
        } else {
            return null;
        }
    }

}
