package me.joshios.interactlog.user;

import java.util.Optional;

public interface Repository<K, V> {

    void add(V value);
    void remove(K key);
    Optional<V> get(K key);
    boolean exists(K key);
}
