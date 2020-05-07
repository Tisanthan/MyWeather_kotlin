package com.example.vadamar.Model;

public class User {
    public String name;
    public String token;

    public User(){

    }

    public User( String name,String token) {
        this.token = token;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
