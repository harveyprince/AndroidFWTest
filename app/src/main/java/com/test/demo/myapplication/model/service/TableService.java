package com.test.demo.myapplication.model.service;

import com.test.demo.myapplication.model.entity.Account;
import com.test.demo.myapplication.model.entity.SignInResult;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by harveyprince on 16/6/28.
 */
public interface TableService {
    @POST("/TableTask/account/signIn")
    Observable<SignInResult> signIn(@Body Account account);
}
