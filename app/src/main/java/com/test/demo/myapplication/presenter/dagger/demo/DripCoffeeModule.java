package com.test.demo.myapplication.presenter.dagger.demo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by harveyprince on 16/6/26.
 */
@Module(
        injects = CoffeeApp.class
)
public class DripCoffeeModule {
    @Provides
    Heater provideHeater() {
        return new ElectricHeater();
    }

    @Provides
    Pump providePump(Thermosiphon pump) {
        return pump;
    }
}