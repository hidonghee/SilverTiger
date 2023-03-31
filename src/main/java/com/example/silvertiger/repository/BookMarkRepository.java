package com.example.silvertiger.repository;



import com.example.silvertiger.dto.BookMarkDto;
import com.example.silvertiger.entity.Account;
import com.example.silvertiger.entity.AccountBookMarkPk;
import com.example.silvertiger.entity.BookMark;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookMarkRepository extends JpaRepository<BookMark, AccountBookMarkPk> {
    @Query(value = "select new com.example.silvertiger.dto.BookMarkDto(b.contextId,b.name,b.url) from BookMark b where b.account = :account ")
    List<BookMarkDto> findAllBookMarks(@Param("account")Account account);
//    Optional<BookMark> findByAccountAndContextId(AccountBookMarkPk accountBookMarkPk);
}