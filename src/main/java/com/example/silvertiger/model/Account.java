package com.example.silvertiger.model;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import javax.persistence.*;
import java.io.Serializable;

// 파라미터가 없는 기본 생성자를 생성, JPA에서는 인자가 없는 생성자를 가져야 함.

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(unique = true ,nullable = false, length = 100)
    private String id;

    @Column(nullable = false, length = 100)
    private String passwd;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String gender;

    @Column(nullable = false, length = 45)
    private String address;

    @Column(nullable = false, length = 45)
    private String tel;

    @Column(nullable = false, length = 45)
    private String city;

    @Column(nullable = false, length = 45)
    private String date;


}

