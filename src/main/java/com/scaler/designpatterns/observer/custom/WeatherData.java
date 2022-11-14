package com.scaler.designpatterns.observer.custom;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of Subject which is WeatherData.
 *
 * @author sudhir on 31-Aug-2020
 */
public class WeatherData implements Subject {
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int index = observers.indexOf(o);
        if (index >= 0) {
            observers.remove(index);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            // here, the observers don't need to know the type of the Subject,
            // as the subject just PUSHES all the information to all the observers.
            // This results in loose coupling between Subject and Observers.
            observer.update(temperature, pressure, humidity);
        }
    }

    public void setMeasurements(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        measurementsChanged();
    }

    public void measurementsChanged() {
        notifyObservers();
    }
}
