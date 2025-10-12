package me.DNFneca.VBlocks.Serializer;

import com.google.gson.*;
import org.bukkit.NamespacedKey;
import me.DNFneca.VBlocks.CustomPlayer.Option.PlayerOption;
import me.DNFneca.VBlocks.CustomPlayer.Option.PlayerOptions;

import java.lang.reflect.Type;
import java.util.List;

public class PlayerOptionDeserializer extends CustomDeserializer<PlayerOption> {

    public PlayerOptionDeserializer() {
        super(PlayerOption.class);
    }

    @Override
    public PlayerOption deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        List<String> list = obj.keySet().stream().toList();
        if (list.getFirst() == null) return null;
        NamespacedKey namespacedKey = NamespacedKey.fromString(list.getFirst());
        Object apply = PlayerOptions.PlayerOptions.get(namespacedKey).secondValue().apply(obj.get(list.getFirst()).getAsString());
        return new PlayerOption(apply, apply.getClass());
    }
}
