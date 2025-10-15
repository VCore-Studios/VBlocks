package me.DNFneca.VBlocks.GUI.GUI.GUIItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GUIItemClickOptions {
    boolean showClickEvent;
    boolean showShortDescription;

    public static GUIItemClickOptions of(boolean showClickEvent, boolean showShortDescription) {
        GUIItemClickOptions options = new GUIItemClickOptions();
        options.setShowClickEvent(showClickEvent);
        options.setShowShortDescription(showShortDescription);
        return options;
    }
}