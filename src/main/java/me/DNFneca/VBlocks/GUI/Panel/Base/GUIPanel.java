package me.DNFneca.VBlocks.GUI.Panel.Base;

import lombok.Getter;
import lombok.Setter;
import me.DNFneca.VBlocks.GUI.GUI.Base.PanelGUI;
import me.DNFneca.VBlocks.GUI.GUI.GUIOption.GUIOptions;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelField.GUIPanelItems;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelField.GUIPanelPlacementOptions;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelItem.GUIPanelItem;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelOption.GUIPanelPlaceOption.GUIPanelPlaceOption;
import me.DNFneca.VBlocks.Manager.CustomPlayerManager;
import me.DNFneca.VBlocks.Registry.RegistryReference;
import me.DNFneca.VBlocks.VBlocks;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.util.Map;

public abstract class GUIPanel {
    @Getter
    @Setter
    private int width = 7, height = 6, slotStartingPoint;
    @Getter
    @Setter
    private boolean isDirty = true;
    @Getter
    private final PanelGUI parent;
    @Getter
    private final GUIOptions guiOptions = new GUIOptions();
    @Getter
    private final GUIPanelPlacementOptions placementOptions = new GUIPanelPlacementOptions();


    public GUIPanel(PanelGUI parent) {
        this.parent = parent;
    }

    public void putItem(int slot, GUIPanelItem item) {
        getParent().getPanelItemMap().putField(slot, item);
        isDirty = true;
    }

    public void putItems(Map<Integer, GUIPanelItem> items) {
        getParent().getPanelItemMap().putFields(items);
        isDirty = true;
    }

    public GUIPanelItem getItem(int slot) {
        return getParent().getPanelItemMap().getField(slot);
    }

    public GUIPanelItems getItems() {
        return getParent().getPanelItemMap();
    }

    public void removeItem(int slot) {
        getParent().getPanelItemMap().removeField(slot);
    }

    public int getSize() {
        return (height - 1) * 9 + (width - 1);
    }

    public void build(Player player) {
        if (!getParent().getOptions().isShouldSetBackground()) return;
        placeOptions();
        isDirty = false;
    }

    private void placeOption(RegistryReference<GUIPanelPlaceOption> optionReference) {
        GUIPanelPlaceOption option = optionReference.value();
        Integer slot = option.getPlaceFunction().apply(this);
        if (slot == null || slot < 0 || slot > getSize()) return;
        getItems().putField(slot, option.getGuiItem());
    }

    private void placeOptions() {
        if (placementOptions.isEmpty()) return;
        for (RegistryReference<GUIPanelPlaceOption> placement : placementOptions.getFields()) {
            placeOption(placement);
        }
    }
}
