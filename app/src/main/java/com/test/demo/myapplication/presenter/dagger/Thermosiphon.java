package com.test.demo.myapplication.presenter.dagger;

import javax.inject.Inject;

/**
 * Created by harveyprince on 16/6/26.
 */
public class Thermosiphon implements Pump {
    private final Heater heater;

    @Inject
    Thermosiphon(Heater heater) {
        this.heater = heater;
    }


    @Override
    public void brew() {
        heater.brew();
        System.out.println("Thermosiphon brew");
    }
}