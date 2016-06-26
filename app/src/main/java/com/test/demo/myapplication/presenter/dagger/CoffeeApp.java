package com.test.demo.myapplication.presenter.dagger;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * Created by harveyprince on 16/6/26.
 */
public class CoffeeApp implements Runnable {
    @Inject
    CoffeeMaker coffeeMaker;

    @Override
    public void run() {
        coffeeMaker.brew();
    }

}