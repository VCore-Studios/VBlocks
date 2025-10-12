package me.DNFneca.VBlocks.Registry;

import org.bukkit.NamespacedKey;

import java.util.*;

public interface Registry<T> {
    NamespacedKey key();
    RegistryReference<T> register(String id, T value);
    RegistryReference<T> getReference(String id);
    RegistryReference<T> getReference(NamespacedKey key);
    T get(NamespacedKey key);
    Optional<T> getOptional(NamespacedKey key);
    Set<Map.Entry<NamespacedKey, T>> entrySet();
    boolean contains(NamespacedKey key);
    Collection<T> values();
}