package me.DNFneca.VBlocks.CustomPlayer.Option;

import lombok.Getter;
import lombok.Setter;
import me.DNFneca.VBlocks.Registry.SerializerRegistry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

@Setter
public class PlayerOption {
    @NotNull private String value;
    @Getter
    @NotNull private transient Type type;

    public PlayerOption(@NotNull Object value, @NotNull Type type) {
        this.type = type;
        setValue(value);
    }

    public void setValue(@NotNull Object value) {
        this.value = SerializerRegistry.getGson().toJson(value, type);
    }

    public <C> C getValue() {
        return SerializerRegistry.getGson().fromJson(value, type);
    }
}
