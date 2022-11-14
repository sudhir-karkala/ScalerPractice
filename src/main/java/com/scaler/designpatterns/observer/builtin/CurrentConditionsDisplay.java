package com.scaler.designpatterns.observer.builtin;

import java.util.Observable;
import java.util.Observer;

/**
 * Concrete implementation of builtin java.util.Observer interface.
 *
 * @author sudhir on 31-Aug-2020
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private Observable observable;
    private float temperature;
    private float pressure;
    private float humidity;

    public CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
    }

    @Override
    public void update(Observable obs, Object arg) {
        // PULL method where down casting is done to extract specific information.
        // The Observer needs to know the type of Subject to do this which results
        // in tight coupling. Ths Subject will need to provide getters for accessing data
        // e.g. getPressure(), getTemperature() and getHumidity().
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            this.temperature = weatherData.getTemperature();
            this.pressure = weatherData.getPressure();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("CurrentConditionsDisplay :: Temperature = " + temperature
                + " Pressure = " + pressure + " Humidity = " + humidity);
    }
}
