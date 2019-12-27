package com.example.saisurapi;

public class User {
    public String name;
    public String token;

    public User(String email, String token) {
        this.name = email;
        this.token = token;
    }
}
