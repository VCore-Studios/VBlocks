package me.DNFneca.VBlocks.Registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.DNFneca.VBlocks.Serializer.CustomDeserializer;
import me.DNFneca.VBlocks.Serializer.CustomSerializer;
import me.DNFneca.VBlocks.VBlocks;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerializerRegistry {
    private static final Map<Type, List<CustomSerializer<?>>> serializers = new HashMap<>(0);
    private static final Map<Type, List<CustomDeserializer<?>>> deserializers = new HashMap<>(0);
    private static boolean isDirty = false;
    private static Gson gson;

    public static void put(Type classType, CustomSerializer<?> adapter) {
        List<CustomSerializer<?>> adapterList = serializers.get(classType);
        if (adapterList == null) {
            serializers.put(classType, List.of(adapter));
            isDirty = true;
            return;
        }
        adapterList.add(adapter);
        isDirty = true;
    }

    public static void put(Type classType, CustomDeserializer<?> adapter) {
        List<CustomDeserializer<?>> adapterList = deserializers.get(classType);
        if (adapterList == null) {
            deserializers.put(classType, List.of(adapter));
            isDirty = true;
            return;
        }
        adapterList.add(adapter);
        isDirty = true;
    }

    public static Map<Type, List<CustomSerializer<?>>> getSerializers() {
        return Map.copyOf(serializers);
    }

    public static Map<Type, List<CustomDeserializer<?>>> getDeserializers() {
        return Map.copyOf(deserializers);
    }

    public static Gson getGson() {
        if (!isDirty) return gson;
        GsonBuilder builder = new GsonBuilder();
        for (Map.Entry<Type, List<CustomSerializer<?>>> entry : serializers.entrySet()) {
            for (CustomSerializer<?> adapter : entry.getValue()) {
                VBlocks.logger.info(entry.getKey().getTypeName() + " -> " + adapter.toString());
                builder.registerTypeAdapter(entry.getKey(), adapter);
            }
        }
        for (Map.Entry<Type, List<CustomDeserializer<?>>> entry : deserializers.entrySet()) {
            for (CustomDeserializer<?> adapter : entry.getValue()) {
                VBlocks.logger.info(entry.getKey().getTypeName() + " -> " + adapter.toString());
                builder.registerTypeAdapter(entry.getKey(), adapter);
            }
        }
        builder.setPrettyPrinting();
        gson = builder.create();
        isDirty = false;
        return gson;
    }
}
