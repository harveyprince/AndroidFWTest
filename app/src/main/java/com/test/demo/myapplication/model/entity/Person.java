package com.test.demo.myapplication.model.entity;

import javax.inject.Inject;

/**
 * Created by harveyprince on 16/6/26.
 */
public class Person {
    private String name;
    private int age;

    @Inject
    public Person() {
    }

    public String getName() {
        return "Jack";
    }

    public int getAge() {
        return 15;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
