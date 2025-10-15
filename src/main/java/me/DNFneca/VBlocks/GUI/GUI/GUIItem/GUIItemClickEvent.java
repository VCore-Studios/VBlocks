package me.DNFneca.VBlocks.GUI.GUI.GUIItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.DNFneca.VBlocks.GUI.GUI.Base.GUI;
import me.DNFneca.VBlocks.Lore.Lore;

import java.util.function.Consumer;

@AllArgsConstructor
@Getter
public class GUIItemClickEvent {
    private final Consumer<GUI> consumer;
    private final Lore shortDescriptionOfEvent;
    private final GUIItemClickOptions options;

    public static GUIItemClickEvent of(Consumer<GUI> consumer, Lore lore, GUIItemClickOptions options) {
        return new GUIItemClickEvent(consumer, lore, options);
    }

    public static GUIItemClickEvent of(Consumer<GUI> consumer, Lore lore) {
        return new GUIItemClickEvent(consumer, lore, GUIItemClickOptions.of(true, true));
    }

    public static GUIItemClickEvent of(Consumer<GUI> consumer) {
        return new GUIItemClickEvent(consumer, null, GUIItemClickOptions.of(false, false));
    }
}
