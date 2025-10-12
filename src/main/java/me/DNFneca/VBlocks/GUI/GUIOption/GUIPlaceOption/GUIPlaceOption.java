package me.DNFneca.VBlocks.GUI.GUIOption.GUIPlaceOption;

import lombok.Getter;
import me.DNFneca.VBlocks.GUI.GUI.Base.GUI;
import me.DNFneca.VBlocks.GUI.GUIItem.GUIItem;

import java.util.function.Function;

public class GUIPlaceOption {
    @Getter
    private boolean isVisible = true;
    @Getter
    private boolean isVisibleIfNotStated = false;
    @Getter
    private boolean isEditable = true;
    @Getter
    private Function<GUI, Integer> placeFunction;
    @Getter
    private Function<String, Void> searchFunction;
    @Getter
    private GUIItem guiItem;

//    TODO: Load the placement functions by default, add the default state to check when opening inventory, making more stuff implicit

    public GUIPlaceOption setGuiItem(GUIItem guiItem) {
        this.guiItem = guiItem;
        return this;
    }

    public GUIPlaceOption setPlaceFunction(Function<GUI, Integer> placeFunction) {
        this.placeFunction = placeFunction;
        return this;
    }

    public GUIPlaceOption setSearchFunction(Function<String, Void> searchFunction) {
        this.searchFunction = searchFunction;
        return this;
    }

    public GUIPlaceOption setVisibleIfNotStated(boolean visibleIfNotStated) {
        isVisibleIfNotStated = visibleIfNotStated;
        return this;
    }

    public GUIPlaceOption setVisible(boolean visible) {
        isVisible = visible;
        return this;
    }

    public GUIPlaceOption build() {
        isEditable = false;
        return this;
    }

}
