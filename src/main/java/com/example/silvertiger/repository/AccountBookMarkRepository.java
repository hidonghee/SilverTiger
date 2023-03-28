package com.example.silvertiger.repository;

import com.example.silvertiger.entity.AccountBookMark;
import com.example.silvertiger.entity.AccountBookMarkPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountBookMarkRepository extends JpaRepository<AccountBookMark, AccountBookMarkPk> {
}
