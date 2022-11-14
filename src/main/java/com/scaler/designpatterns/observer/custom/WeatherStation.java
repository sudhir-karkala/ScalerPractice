package com.scaler.designpatterns.observer.custom;

/**
 * Driver class to test observer pattern.
 *
 * @author sudhir on 31-Aug-2020
 */
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay =
                new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        weatherData.setMeasurements(97, 50, 66);
        weatherData.setMeasurements(90, 22, 44);
        // can do any operations using Observer objects if needed.
    }
}
