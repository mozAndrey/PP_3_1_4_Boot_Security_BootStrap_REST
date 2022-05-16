package ru.kata.spring.boot_security.demo.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Component
@Table(name = "authorities")
public class Role implements Serializable, GrantedAuthority {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "username")
    private Users users;

    @Column
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    public String getName() {
        return users.getUsername();
    }

    public Users getUsers() {
        return users;
    }

    @Autowired
    public void setUsers(Users users) {
        this.users = users;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
