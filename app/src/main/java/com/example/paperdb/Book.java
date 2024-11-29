package com.example.paperdb;

import java.util.UUID;

public class Book {
    private String id;
    private String title;
    private  String author;
    private String imagePath;

    public Book(String title, String author,String imagePath) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
