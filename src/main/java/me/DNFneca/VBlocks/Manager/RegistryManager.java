package me.DNFneca.VBlocks.Manager;

import org.bukkit.NamespacedKey;
import me.DNFneca.VBlocks.VBlocks;
import me.DNFneca.VBlocks.Registry.CustomRegistry;
import me.DNFneca.VBlocks.Registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class RegistryManager {
    private static final Map<NamespacedKey, Registry<?>> REGISTRIES = new HashMap<>();

    public static <T> Registry<T> createRegistry(NamespacedKey key) {
        if (REGISTRIES.containsKey(key))
            throw new IllegalStateException("Registry already exists: " + key);
        Registry<T> registry = new CustomRegistry<>(key);
        REGISTRIES.put(key, registry);
        return registry;
    }

    public static <T> Registry<T> createRegistry(String name) {
        NamespacedKey namespacedKey = new NamespacedKey(VBlocks.getInstance(), name);
        return createRegistry(namespacedKey);
    }

    @SuppressWarnings("unchecked")
    public static <T> Registry<T> getRegistry(NamespacedKey key) {
        return (Registry<T>) REGISTRIES.get(key);
    }
}