package com.example.collagedashboardapplication;

public class Student {

    private int id;
    private String name;
    private String birthdate;

    public Student(int id, String name, String birthdate)
    {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

}
