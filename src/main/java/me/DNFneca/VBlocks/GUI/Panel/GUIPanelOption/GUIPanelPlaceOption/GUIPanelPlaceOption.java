package me.DNFneca.VBlocks.GUI.Panel.GUIPanelOption.GUIPanelPlaceOption;

import lombok.Getter;
import me.DNFneca.VBlocks.GUI.GUI.GUIItem.GUIItem;
import me.DNFneca.VBlocks.GUI.Panel.Base.GUIPanel;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelItem.GUIPanelItem;

import java.util.function.Function;

public class GUIPanelPlaceOption {
    @Getter
    private boolean isEditable = true;
    @Getter
    private Function<GUIPanel, Integer> placeFunction;
    @Getter
    private Function<String, Void> searchFunction;
    @Getter
    private GUIPanelItem guiItem;

//    TODO: Load the placement functions by default, add the default state to check when opening inventory, making more stuff implicit

    public GUIPanelPlaceOption setGuiItem(GUIPanelItem guiItem) {
        this.guiItem = guiItem;
        return this;
    }

    public GUIPanelPlaceOption setPlaceFunction(Function<GUIPanel, Integer> placeFunction) {
        this.placeFunction = placeFunction;
        return this;
    }

    public GUIPanelPlaceOption setSearchFunction(Function<String, Void> searchFunction) {
        this.searchFunction = searchFunction;
        return this;
    }

    public GUIPanelPlaceOption build() {
        isEditable = false;
        return this;
    }

}
