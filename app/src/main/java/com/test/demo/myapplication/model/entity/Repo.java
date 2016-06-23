package com.test.demo.myapplication.model.entity;

/**
 * Created by harveyprince on 16/6/23.
 */
public class Repo {
    private String full_name;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    @Override
    public String toString() {
        return full_name;
    }
}
