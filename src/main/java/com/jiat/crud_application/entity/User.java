package com.jiat.crud_application.entity;

import jakarta.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.getUserByEmailAndPassword", query = "SELECT u FROM User u WHERE u.name=:name AND u.password=:password")
})
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    public int getId() {
        return id;
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
