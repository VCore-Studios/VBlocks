package me.DNFneca.VBlocks.Serializer;

import com.google.gson.*;
import org.bukkit.NamespacedKey;
import me.DNFneca.VBlocks.CustomPlayer.Option.PlayerOption;
import me.DNFneca.VBlocks.CustomPlayer.Option.PlayerOptions;
import me.DNFneca.VBlocks.Util.Triplet;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Function;

public class PlayerOptionSerializer extends CustomSerializer<PlayerOption> {

    public PlayerOptionSerializer() {
        super(PlayerOption.class);
    }

    @Override
    public JsonElement serialize(PlayerOption src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<NamespacedKey, Triplet<Type, Function<String, Object>, Function<Object, String>>> entry : PlayerOptions.PlayerOptions.entrySet()) {
            if (src.getValue() == null || entry.getValue().firstValue() != src.getType()) continue;
            jsonObject.addProperty(
                    entry.getKey().toString(),
                    entry.getValue().thirdValue().apply(src.getValue())
            );
        }
        return jsonObject;
    }
}