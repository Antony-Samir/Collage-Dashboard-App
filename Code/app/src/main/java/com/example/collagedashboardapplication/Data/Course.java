package com.example.collagedashboardapplication.Data;

import java.util.HashMap;

public class Course {

    private int id;
    private String name;
    private int hours;
    private String book;
    private HashMap<Student, Boolean> attendees;

    public Course(int id, String name, int hours, String book) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public int getHours() {
        return hours;
    }


    public String getBook() {
        return book;
    }

}
