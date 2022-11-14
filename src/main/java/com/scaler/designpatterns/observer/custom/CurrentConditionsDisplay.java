package com.scaler.designpatterns.observer.custom;

/**
 * Concrete implementation of Observer.
 *
 * @author sudhir on 31-Aug-2020
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private WeatherData weatherData;
    private float temperature;
    private float pressure;
    private float humidity;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
        System.out.println("CurrentConditionsDisplay :: Temperature = " + temperature
                + " Pressure = " + pressure + " Humidity = " + humidity);
    }
}
