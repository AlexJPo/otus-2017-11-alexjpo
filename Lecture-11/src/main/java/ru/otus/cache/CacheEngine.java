package ru.otus.cache;

public interface CacheEngine<K, V> {
    <T> void put(Long id, T element);

    <T> T get(K key, Class<T> element);

    int getHitCount();

    int getMissCount();

    void dispose();
}
