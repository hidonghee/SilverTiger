package com.example.silvertiger.dto;

import com.example.silvertiger.entity.Account;
import com.example.silvertiger.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
// 겟, 셋 메서드를 자동으로 만들어준다.
@ToString
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자

//데이터를 전송할 때 사용하는 객체

public class BoardDto {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private Account account;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;


    public BoardDto(Long id, String boardWriter, String boardPass, String boardTitle, String boardContents, int boardHits, LocalDateTime boardCreatedTime, LocalDateTime boardUpdatedTime){
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardPass = boardPass;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
        this.boardUpdatedTime = boardUpdatedTime;
    }

    //엔티티 객체를 Dto에 옮겨 담는다.
    public static BoardDto toBoardDto(BoardEntity boardEntity){
        BoardDto boardDto = new BoardDto();
        boardDto.setId(boardEntity.getId());
        boardDto.setAccount(boardEntity.getAccount());
        boardDto.setBoardWriter(boardEntity.getBoardWriter());
        boardDto.setBoardPass(boardEntity.getBoardPass());
        boardDto.setBoardTitle(boardEntity.getBoardTitle());
        boardDto.setBoardContents(boardEntity.getBoardContents());
        boardDto.setBoardHits(boardEntity.getBoardHits());
        boardDto.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDto.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        return boardDto;
    }
}
