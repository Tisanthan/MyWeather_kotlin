package com.example.saisurapi;

public class User {
    public String name;
    public String token;

    public User(){

    }

    public User( String name,String token) {
        this.token = token;
        this.name = name;
    }
}
