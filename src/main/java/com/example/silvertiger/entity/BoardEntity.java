package com.example.silvertiger.entity;

import com.example.silvertiger.dto.BoardDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "board")

public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false,length = 50, name = "contextId")
    private String contextId;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String boardWriter;

    @Column (nullable = false)
    private String boardPass;

    @Column (nullable = false)
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column (nullable = false)
    private int boardHits;

    //엔티티 객체를 옮겨 담기 위해 객체를 생성
    public static BoardEntity toSaveEntity(BoardDto boardDto, Account account){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setAccount(account);
        boardEntity.setContextId(boardDto.getContext_id());
        boardEntity.setBoardWriter(boardDto.getBoardWriter());
        boardEntity.setBoardPass(boardDto.getBoardPass());
        boardEntity.setBoardTitle(boardDto.getBoardTitle());
        boardEntity.setBoardContents(boardDto.getBoardContents());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDto boardDto, Account account) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDto.getId());
        boardEntity.setAccount(account);
        boardEntity.setContextId(boardDto.getContext_id());
        boardEntity.setBoardWriter(boardDto.getBoardWriter());
        boardEntity.setBoardPass(boardDto.getBoardPass());
        boardEntity.setBoardTitle(boardDto.getBoardTitle());
        boardEntity.setBoardContents(boardDto.getBoardContents());
        boardEntity.setBoardHits(boardDto.getBoardHits());
        return boardEntity;
    }
}
