package ru.otus.mementoobserver;

public interface IObserved {
    void addObserver(IObserver o);
    void removeObserver(IObserver o);
    void notifyObservers();
}
