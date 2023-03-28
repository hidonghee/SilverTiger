package com.example.silvertiger.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookmark")
public class BookMark implements Serializable {

    @Id
    @Column(unique = true, nullable = false,length = 50, name = "contentId")
    private String contentId;

    @OneToMany(mappedBy = "bookMarkId")
    public List<AccountBookMark> accountBookMark = new ArrayList<>();
}
