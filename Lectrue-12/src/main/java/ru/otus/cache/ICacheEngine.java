package ru.otus.cache;

public interface ICacheEngine <K, V> {
    <T> void put(T element);

    <T> T get(K key);

    int getHitCount();

    int getMissCount();

    void dispose();
}
