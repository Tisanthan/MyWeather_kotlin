package com.example.vadamar.Model;

public class ListItem {

    public String title;
    public String author;
    public String date;
    public String image;

    public ListItem(String title, String author, String date, String image) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }
}
