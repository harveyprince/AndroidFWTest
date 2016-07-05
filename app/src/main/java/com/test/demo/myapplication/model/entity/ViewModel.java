package com.test.demo.myapplication.model.entity;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;


import com.test.demo.myapplication.BR;
import com.test.demo.myapplication.R;

import me.tatarka.bindingcollectionadapter.ItemView;

/**
 * Created by harveyprince on 16/7/5.
 */
public class ViewModel {
    public final ObservableList<Repo> items = new ObservableArrayList<>();

    public final ItemView itemView = ItemView.of(BR.item, R.layout.item_repo);
}
