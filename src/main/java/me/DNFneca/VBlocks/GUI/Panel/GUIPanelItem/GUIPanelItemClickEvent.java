package me.DNFneca.VBlocks.GUI.Panel.GUIPanelItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.DNFneca.VBlocks.GUI.GUI.Base.GUI;
import me.DNFneca.VBlocks.GUI.Panel.Base.GUIPanel;
import me.DNFneca.VBlocks.Lore.Lore;

import java.util.function.Consumer;

@AllArgsConstructor
@Getter
public class GUIPanelItemClickEvent {
    private final Consumer<GUIPanel> consumer;
    private final Lore shortDescriptionOfEvent;
    private final GUIPanelItemClickOptions options;

    public static GUIPanelItemClickEvent of(Consumer<GUIPanel> consumer, Lore lore, GUIPanelItemClickOptions options) {
        return new GUIPanelItemClickEvent(consumer, lore, options);
    }

    public static GUIPanelItemClickEvent of(Consumer<GUIPanel> consumer, Lore lore) {
        return new GUIPanelItemClickEvent(consumer, lore, GUIPanelItemClickOptions.of(true, true));
    }

    public static GUIPanelItemClickEvent of(Consumer<GUIPanel> consumer) {
        return new GUIPanelItemClickEvent(consumer, null, GUIPanelItemClickOptions.of(false, false));
    }
}
