package com.test.demo.myapplication.model.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.okhttp.ResponseBody;
import com.test.demo.myapplication.model.entity.Repo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by harveyprince on 16/6/23.
 */
public interface GithubService {
    @GET("/users/{user}/repos")
    Observable<List<Repo>> listRepos(@Path("user") String user);
}
