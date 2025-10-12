package me.DNFneca.VBlocks.Util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class ComponentUtils {

    public static String serializeComponent(final Component component) {
        return GsonComponentSerializer.gson().serialize(component);
    }

    public static Component deserializeComponent(final String serializedComponent) {
        return GsonComponentSerializer.gson().deserialize(serializedComponent);
    }

    public static String serializeComponentAsString(final Component component) {
        return PlainTextComponentSerializer.plainText().serialize(component);
    }

    public static Component deserializeStringAsComponent(final String serializedComponent) {
        return PlainTextComponentSerializer.plainText().deserialize(serializedComponent);
    }
}
