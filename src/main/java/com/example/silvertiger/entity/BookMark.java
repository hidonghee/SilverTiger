package com.example.silvertiger.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(AccountBookMarkPk.class)
@Table(name = "bookmark")
public class BookMark implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
//    private Long id;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @Id
    @Column(nullable = false,length = 50, name = "contextId")
    private String contextId;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 250)
    private String url;

    public void setAccount(Account account){
        this.account = account;
        account.getBookMarks().add(this);
    }
    public void removeAccount(Account account){
        this.account=null;
        account.getBookMarks().add(this);
    }

}
