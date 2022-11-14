package com.scaler.designpatterns.observer.builtin;

import java.util.Observable;
import java.util.Observer;

/**
 * Concrete implementation of builtin java.util.Observer interface.
 *
 * @author sudhir on 31-Aug-2020
 */
public class StatisticsDisplay implements Observer, DisplayElement {
    private Observable observable;
    private float temperature;
    private float pressure;
    private float humidity;

    public StatisticsDisplay(Observable observable) {
        this.observable = observable;
        this.observable.addObserver(this);
    }

    @Override
    public void update(Observable obs, Object arg) {
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
        System.out.println("StatisticsDisplay :: Temperature = " + temperature
                + " Pressure = " + pressure + " Humidity = " + humidity);
    }
}
