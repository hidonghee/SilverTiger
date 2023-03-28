package com.example.silvertiger.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class AccountBookMarkPk implements Serializable {
    private Account account;
    private BookMark bookMark;
}
