package com.example.silvertiger.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountBookMarkPk implements Serializable {
    private Account account;
    private String contextId;

    //    private Long id;
    @Override
    public int hashCode() {
        return Objects.hash(account, contextId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AccountBookMarkPk other = (AccountBookMarkPk) obj;
        return account == other.account && contextId == other.contextId;
    }
}
