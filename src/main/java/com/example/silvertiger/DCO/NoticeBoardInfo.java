package com.example.silvertiger.DCO;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import javax.persistence.*;
import java.util.List;

@Entity
public class NoticeBoardInfo {
    @Id
    @Column(name = "id", length = 100)
    private String id;

    @Column(name = "bookmark", columnDefinition = "TINYINT(1)")
    private boolean bookmark;

    @Column(nullable = false, length = 45)
    private String galContentId;

    @ManyToMany
    @JoinTable(
            name = "NoticeBoardInfo_Account",
            joinColumns = @JoinColumn(name = "NoticeBoardInfo_id"),
            inverseJoinColumns = @JoinColumn(name = "accout_id"))
    private List<NoticeBoardInfo> employees;
}
