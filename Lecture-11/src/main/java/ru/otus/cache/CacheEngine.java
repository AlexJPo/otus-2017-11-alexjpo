package ru.otus.cache;

public interface CacheEngine<K, V> {
    <T> void put(T element);

    <T> T get(K key);

    int getHitCount();

    int getMissCount();

    void dispose();
}
