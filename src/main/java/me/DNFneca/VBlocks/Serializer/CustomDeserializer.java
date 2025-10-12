package me.DNFneca.VBlocks.Serializer;

import com.google.gson.JsonDeserializer;
import me.DNFneca.VBlocks.Registry.SerializerRegistry;
import org.jetbrains.annotations.ApiStatus;

import java.lang.reflect.Type;

@Deprecated
@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
public abstract class CustomDeserializer<T> implements JsonDeserializer<T> {
    public CustomDeserializer(Type tClass) {
        SerializerRegistry.put(tClass, this);
    }
}
