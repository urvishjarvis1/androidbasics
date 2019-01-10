package com.example.urvish.assignment7.model;

/**
 * Created by urvish on 16/2/18.
 * POJO class for retrofit model
 */

public class Student {
    private String Id;
    private String name;
    private String email;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
