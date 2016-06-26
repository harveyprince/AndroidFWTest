package com.test.demo.myapplication.presenter.dagger;

/**
 * Created by harveyprince on 16/6/26.
 */
public class ElectricHeater implements Heater {
    @Override
    public void brew() {
        System.out.println("ElectricHeater brew");
    }
}
