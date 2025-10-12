package me.DNFneca.VBlocks.Registry;

import org.bukkit.NamespacedKey;

public class RegistryReference<T> {
    private final NamespacedKey key;
    private final Registry<T> registry;

    public RegistryReference(NamespacedKey key, Registry<T> registry) {
        this.key = key;
        this.registry = registry;
    }

    public NamespacedKey key() {
        return key;
    }

    public T value() {
        return registry.get(key);
    }

    @Override
    public String toString() {
        return key.toString();
    }
}