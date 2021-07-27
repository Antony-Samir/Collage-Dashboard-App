package com.example.collagedashboardapplication.Data;

public abstract class User
{
    public String name;
    public int Password;

    User(String name, int password)
    {
        this.name = name;
        this.Password = password;
    }
}
