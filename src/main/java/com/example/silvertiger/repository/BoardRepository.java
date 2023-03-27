package com.example.silvertiger.repository;

import com.example.silvertiger.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//String, id로 변경하기
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Modifying
    //update board_table set board_hits = board_hits+1 where id =?
    //update BoardEntity -> 테이블 이름이 오는데 엔티티를 이용하기 때문에 엔티티 이름이 온다, 엔티티는 약어를 사용
    //Param과 id가 매칭된다.
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id);
}
