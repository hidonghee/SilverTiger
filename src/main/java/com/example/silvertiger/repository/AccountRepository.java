package com.example.silvertiger.repository;

import com.example.silvertiger.entity.Account;
import com.example.silvertiger.entity.BookMark;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
//    @EntityGraph(attributePaths = {"bookMarks"})
    Optional<Account> findById(String id);

    Optional<Account> findBookMarksById(String Id);



}
