package com.test.demo.myapplication.presenter.dagger.demo;

import javax.inject.Inject;

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