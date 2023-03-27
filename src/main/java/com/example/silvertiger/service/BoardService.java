package com.example.silvertiger.service;

import com.example.silvertiger.dto.BoardDto;
import com.example.silvertiger.entity.BoardEntity;
import com.example.silvertiger.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// DTO -> Entity (Entity 클래스로 변환)
// Entity -> DTO (DTO 클래슬 변환)

@Service
@RequiredArgsConstructor

public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDto boardDto){
        // BoardEntity.toSaveEntity(boardDto); 이 매서드를 호출한 결과는 엔티티 객체로 받아올 수 있다.
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDto);
        //save 매서드는 엔티티 타입으로 리턴
        boardRepository.save(boardEntity);
    }

    public List<BoardDto> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        //findall을 하면 레포지토리에서 가져오면 엔티티로 오게된다 -> 여러개이기 때문에 List형태

        List<BoardDto> boardDtoList = new ArrayList<>();
        //Dto형태로 돌려줘야한다.

        //반복문을 돌려서 하나씩 받는다.
        //반복문의 엔티티 객체를 Dto로 전환하고 전환된 것을 리스트로 저장
        for (BoardEntity boardEntity: boardEntityList){
            boardDtoList.add(BoardDto.toBoardDto(boardEntity));
        }
        return boardDtoList;
    }

    @Transactional
    public void updateHits(Long id){
        boardRepository.updateHits(id);
    }

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

    public BoardDto update(BoardDto boardDto) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDto);
        boardRepository.save(boardEntity);
        return findById(boardDto.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
