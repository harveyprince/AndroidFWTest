package com.test.demo.myapplication.presenter.dagger;

import javax.inject.Inject;

/**
 * Created by harveyprince on 16/6/26.
 */
public class CoffeeMaker {
    @Inject
    Heater heater;
    @Inject
    Pump pump;

    public void brew(){
        heater.brew();
        pump.brew();
        System.out.println("brew");
    }
}