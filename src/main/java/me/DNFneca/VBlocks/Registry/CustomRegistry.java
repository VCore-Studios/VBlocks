package me.DNFneca.VBlocks.Registry;

import org.bukkit.NamespacedKey;
import me.DNFneca.VBlocks.VBlocks;

import java.util.*;

public class CustomRegistry<T> implements Registry<T> {
    private final NamespacedKey key;
    private final Map<NamespacedKey, T> entries = new HashMap<>();

    public CustomRegistry(NamespacedKey key) {
        this.key = key;
    }

    public CustomRegistry(String name) {
        this.key = new NamespacedKey(VBlocks.getInstance(), name);
    }

    @Override
    public NamespacedKey key() {
        return key;
    }

    @Override
    public RegistryReference<T> register(String id, T value) {
        NamespacedKey namespacedKey = new NamespacedKey(key.getNamespace(), id);
        if (entries.containsKey(namespacedKey))
            throw new IllegalStateException("Duplicate entry: " + namespacedKey);
        entries.put(namespacedKey, value);
        return new RegistryReference<>(namespacedKey, this);
    }

    @Override
    public RegistryReference<T> getReference(String id) {
        NamespacedKey namespacedKey = new NamespacedKey(key.getNamespace(), id);
        if (!entries.containsKey(namespacedKey))
            throw new IllegalStateException("Entry doesn't exist: " + namespacedKey);
        return new RegistryReference<>(namespacedKey, this);
    }

    @Override
    public RegistryReference<T> getReference(NamespacedKey key) {
        if (!entries.containsKey(key))
            throw new IllegalStateException("Entry doesn't exist: " + key);
        return new RegistryReference<>(key, this);
    }

    @Override
    public T get(NamespacedKey key) {
        T value = entries.get(key);
        if (value == null)
            throw new IllegalArgumentException("Unknown key: " + key);
        return value;
    }

    @Override
    public Optional<T> getOptional(NamespacedKey key) {
        return Optional.ofNullable(entries.get(key));
    }

    @Override
    public boolean contains(NamespacedKey key) {
        return entries.containsKey(key);
    }

    @Override
    public Set<Map.Entry<NamespacedKey, T>> entrySet() {
        return Collections.unmodifiableSet(entries.entrySet());
    }

    @Override
    public Collection<T> values() {
        return Collections.unmodifiableCollection(entries.values());
    }
}