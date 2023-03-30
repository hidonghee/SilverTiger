package com.example.silvertiger.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

@IdClass(AccountBookMarkPk.class)
@Table(name = "bookmark")
public class BookMark implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @Id
    @Column(unique = true, nullable = false,length = 50, name = "contentId")
    private String contextId;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 250)
    private String url;

    public void setAccount(Account account){
        this.account = account;
        account.getBookMarks().add(this);
    }


//    @OneToMany(mappedBy = "bookMarkId")
//    public List<AccountBookMark> accountBookMark = new ArrayList<>();
}
