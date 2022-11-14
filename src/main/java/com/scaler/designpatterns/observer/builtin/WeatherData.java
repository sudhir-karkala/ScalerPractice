package com.scaler.designpatterns.observer.builtin;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Concrete class WeatherData which extends builtin class java.util.Observable.
 *
 * @author sudhir on 31-Aug-2020
 */
public class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        super();
    }

    public void setMeasurements(float temperature, float pressure, float humidity) {
        setChanged();
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        measurementsChanged();
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
