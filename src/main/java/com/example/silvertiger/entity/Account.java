package com.example.silvertiger.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

// 파라미터가 없는 기본 생성자를 생성, JPA에서는 인자가 없는 생성자를 가져야 함.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "account")
public class Account implements UserDetails, Serializable {
    @Id
    @Column(unique = true, nullable = false, length = 100, name= "id")
    private String id;

    @Column(nullable = false, length = 250)
    private String passwd;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(nullable = false, length = 45)
    private String tel;

    @Column(nullable = false, length = 45)
    private String date;

//    @OneToMany(mappedBy = "accountId")
//    public List<AccountBookMark> bookMark = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"account"})
    private Set<BookMark> bookMarks = new LinkedHashSet<>();

    public void addBookMark(BookMark bookMark){
        bookMarks.add(bookMark);
        bookMark.setAccount(this);
    }


    @ElementCollection(fetch = FetchType.EAGER) //roles 컬렉션
    @Builder.Default
    private List<String> roles = new ArrayList<>();


    @Override   //사용자의 권한 목록 리턴
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public String getPassword() {return null;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

