package com.test.demo.myapplication.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by harveyprince on 16/6/28.
 */
public class Account {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
