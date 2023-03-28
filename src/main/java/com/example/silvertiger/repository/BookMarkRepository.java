package com.example.silvertiger.repository;

import com.example.silvertiger.entity.AccountBookMark;

import com.example.silvertiger.entity.BookMark;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookMarkRepository extends JpaRepository<BookMark,String> {

}
