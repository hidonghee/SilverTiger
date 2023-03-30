package com.example.silvertiger.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class AccountBookMarkPk implements Serializable {
    private Account account;
    private String contextId;

}
