package me.DNFneca.VBlocks.GUI.GUI.Base;

import lombok.Getter;
import lombok.Setter;
import me.DNFneca.VBlocks.GUI.GUIItem.GUIItem;

import java.util.HashMap;
import java.util.Map;

public class ScrollableGUI {
    @Getter
    @Setter
    private int scrollIndex = 0;
    @Getter
    private final Map<Integer, GUIItem> visibleItems = new HashMap<>(0);
}
