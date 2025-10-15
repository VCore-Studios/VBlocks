package me.DNFneca.VBlocks.GUI.Panel.GUIPanelItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GUIPanelItemOptions {
    boolean enchant;

    public static GUIPanelItemOptions of(boolean enchant) {
        GUIPanelItemOptions options = new GUIPanelItemOptions();
        options.setEnchant(enchant);
        return options;
    }
}
