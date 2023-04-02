package com.example.silvertiger.repository;

import com.example.silvertiger.dto.BoardDto;
import com.example.silvertiger.entity.Account;
import com.example.silvertiger.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//String, id로 변경하기
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id);

    @Query("SELECT new com.example.silvertiger.dto.BoardDto(b.id,b.boardWriter,b.boardPass,b.boardTitle, b.boardContents, b.boardHits, b.createdTime, b.updatedTime, b.contextId) FROM BoardEntity b")
    List<BoardDto> findAllBoardDto();

    @Query("SELECT new com.example.silvertiger.dto.BoardDto(b.id,b.boardWriter,b.boardPass,b.boardTitle, b.boardContents, b.boardHits, b.createdTime, b.updatedTime, b.contextId) FROM BoardEntity b where b.account = :account")
    List<BoardDto> findAccountBoardDto(@Param("account") Account account);

    @Query("SELECT new com.example.silvertiger.dto.BoardDto(b.id,b.boardWriter,b.boardPass,b.boardTitle, b.boardContents, b.boardHits, b.createdTime, b.updatedTime, b.contextId) FROM BoardEntity b where b.contextId = :context")
    List<BoardDto> findContext(@Param("context") String contextId);
}
