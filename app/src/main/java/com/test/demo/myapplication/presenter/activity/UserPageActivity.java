package com.test.demo.myapplication.presenter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.demo.myapplication.R;
import com.test.demo.myapplication.model.entity.Person;

import javax.inject.Inject;

public class UserPageActivity extends AppCompatActivity {

    @Inject
    Person person;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
    }
}
