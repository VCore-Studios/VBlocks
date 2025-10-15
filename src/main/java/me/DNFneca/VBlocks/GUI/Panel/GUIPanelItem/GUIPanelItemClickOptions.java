package me.DNFneca.VBlocks.GUI.Panel.GUIPanelItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GUIPanelItemClickOptions {
    boolean showClickEvent;
    boolean showShortDescription;

    public static GUIPanelItemClickOptions of(boolean showClickEvent, boolean showShortDescription) {
        GUIPanelItemClickOptions options = new GUIPanelItemClickOptions();
        options.setShowClickEvent(showClickEvent);
        options.setShowShortDescription(showShortDescription);
        return options;
    }
}