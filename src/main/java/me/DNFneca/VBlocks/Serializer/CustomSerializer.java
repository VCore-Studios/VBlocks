package me.DNFneca.VBlocks.Serializer;

import com.google.gson.JsonSerializer;
import me.DNFneca.VBlocks.Registry.SerializerRegistry;
import org.jetbrains.annotations.ApiStatus;

import java.lang.reflect.Type;

@Deprecated
@ApiStatus.ScheduledForRemoval(inVersion = "0.2.0")
public abstract class CustomSerializer<T> implements JsonSerializer<T> {
    public CustomSerializer(Type tClass) {
        SerializerRegistry.put(tClass, this);
    }
}
