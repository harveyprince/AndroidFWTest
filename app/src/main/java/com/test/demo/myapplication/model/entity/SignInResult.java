package com.test.demo.myapplication.model.entity;

/**
 * Created by harveyprince on 16/6/28.
 */
public class SignInResult {
    private int success;
    private String message;
    private long user_id;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
