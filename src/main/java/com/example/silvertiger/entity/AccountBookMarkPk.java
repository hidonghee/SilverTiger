package com.example.silvertiger.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class AccountBookMarkPk implements Serializable {
    private String accountId;
    private String bookMarkId;
}
