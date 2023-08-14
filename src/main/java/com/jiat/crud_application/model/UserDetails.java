package com.jiat.crud_application.model;

public class UserDetails  {

    private String name;
    private String password;

    public UserDetails(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserDetails() {
    }

    public String getName() {
        return name;
    }

    public void setName(String email) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
