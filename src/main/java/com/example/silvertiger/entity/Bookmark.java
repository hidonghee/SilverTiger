package com.example.silvertiger.entity;

import javax.persistence.*;
import java.util.List;

@Entity

public class Bookmark {
    @Id
    @Column(unique = true ,nullable = false, length = 100)
    private String id;

    @Column(nullable = false, length = 100)
    private String galContentId;

    @ManyToMany
    @JoinTable(
            name = "Bookmark_Account",
            joinColumns = @JoinColumn(name = "Bookmark_id"),
            inverseJoinColumns = @JoinColumn(name = "Account_id"))
    private List<Bookmark> bookmark;
}