package com.test.demo.myapplication.model.entity;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Repo> getList(){
        List<Repo> list = new ArrayList<>();
        Repo rp = new Repo();
        rp.setFull_name("hhhhh");
        list.add(rp);
        list.add(rp);
        list.add(rp);
        list.add(rp);
        list.add(rp);
        return list;
    }
}
