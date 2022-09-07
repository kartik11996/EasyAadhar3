package com.stackroute.authenticationservice.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private String email;
    private String password;
    private String fullName;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name="Users", referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name="role",referencedColumnName = "id") )

    private Set<Role> roles=new HashSet<>();
}
