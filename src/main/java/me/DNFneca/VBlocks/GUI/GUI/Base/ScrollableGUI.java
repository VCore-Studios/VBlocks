package me.DNFneca.VBlocks.GUI.GUI.Base;

import lombok.Getter;
import lombok.Setter;
import me.DNFneca.VBlocks.GUI.GUI.GUIItem.GUIItem;
import net.kyori.adventure.text.Component;

import java.util.HashMap;
import java.util.Map;

public abstract class ScrollableGUI extends GUI {
    @Getter
    @Setter
    private int scrollIndex = 0;
    @Getter
    private final Map<Integer, GUIItem> visibleItems = new HashMap<>(0);

    public ScrollableGUI(Component title, int size) {
        super(title, size);
    }
}
