package me.DNFneca.VBlocks.GUI.GUIItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GUIItemOptions {
    boolean enchant;

    public static GUIItemOptions of(boolean enchant) {
        GUIItemOptions options = new GUIItemOptions();
        options.setEnchant(enchant);
        return options;
    }
}
