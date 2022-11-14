package com.scaler.designpatterns.observer.custom;

/**
 * All observers implement this interface.
 *
 * @author sudhir on 31-Aug-2020
 */
public interface Observer {
    public void update(float temperature, float pressure, float humidity);
}
