package me.DNFneca.VBlocks.Serializer;

import com.google.gson.*;
import org.bukkit.NamespacedKey;

import java.lang.reflect.Type;

public class NamespacedKeyDeserializer extends CustomDeserializer<NamespacedKey> {
    public NamespacedKeyDeserializer() {
        super(NamespacedKey.class);
    }

    @Override
    public NamespacedKey deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        if (obj.get("namespace") == null) return null;
        return NamespacedKey.fromString(obj.get("namespace").getAsString());
    }
}
