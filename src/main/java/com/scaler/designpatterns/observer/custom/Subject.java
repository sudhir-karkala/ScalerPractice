package com.scaler.designpatterns.observer.custom;

/**
 * All subjects implement this interface.
 *
 * @author sudhir on 31-Aug-2020
 */
public interface Subject {
    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObservers();
}
