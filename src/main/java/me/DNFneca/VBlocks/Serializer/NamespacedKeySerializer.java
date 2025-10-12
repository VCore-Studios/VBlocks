package me.DNFneca.VBlocks.Serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import org.bukkit.NamespacedKey;

import java.lang.reflect.Type;

public class NamespacedKeySerializer extends CustomSerializer<NamespacedKey> {
    public NamespacedKeySerializer() {
        super(NamespacedKey.class);
    }

    @Override
    public JsonElement serialize(NamespacedKey src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("namespace", src.toString());
        return jsonObject;
    }
}
