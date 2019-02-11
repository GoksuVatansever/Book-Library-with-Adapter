package com.example.kontess.booklibrary;


/**
 * Created by Kontess on 14.5.2018.
 */

public class Book {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setName(Integer id) {
        this.ID = id;
    }
    public Integer getId() {
        return ID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getDeathyear() {
        return deathyear;
    }

    public void setDeathyear(Integer deathyear) {
        this.deathyear = deathyear;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    private String name;
    private String author;
    private Integer deathyear;
    private String subjects;
    private Integer ID;

    public Book(String name, String author, Integer deathyear, String subjects, Integer id) {
        this.name = name;
        this.author = author;
        this.deathyear = deathyear;
        this.subjects = subjects;
        this.ID = id;
    }







}
