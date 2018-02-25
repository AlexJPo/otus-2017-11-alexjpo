package ru.otus.cache;

import java.util.Map;

public interface ICacheEngine <K, V> {
    <T> void put(Long id, T element);

    <T> T get(K key, Class<T> element);

    int getHitCount();

    int getMissCount();

    void dispose();

    Map<String,Object> getStatus();
}