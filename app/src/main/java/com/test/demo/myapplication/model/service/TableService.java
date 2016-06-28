package com.test.demo.myapplication.model.service;

import com.test.demo.myapplication.model.entity.SignInResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by harveyprince on 16/6/28.
 */
public interface TableService {
    @GET("/TableTask/account/signIn")
    Observable<SignInResult> signIn(@Query("email") String email, @Query("password") String password);
}
