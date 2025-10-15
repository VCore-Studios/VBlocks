package me.DNFneca.VBlocks.GUI.Panel.Base;

import lombok.Getter;
import lombok.Setter;
import me.DNFneca.VBlocks.GUI.GUI.Base.PanelGUI;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelField.GUIPanelItems;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelItem.GUIPanelItem;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelOption.GUIPanelPlaceOption.GUIPanelPlaceOptions;
import org.bukkit.entity.Player;

import java.util.Map;

public abstract class ScrollableGUIPanel extends GUIPanel {
    @Getter
    @Setter
    private int scrollIndex = 0;
    @Getter
    private final GUIPanelItems invisibleItems = new GUIPanelItems();


    public ScrollableGUIPanel(PanelGUI parent) {
        super(parent);
    }

    @Override
    public void putItem(int slot, GUIPanelItem item) {
        invisibleItems.putField(slot, item);
    }

    @Override
    public void putItems(Map<Integer, GUIPanelItem> items) {
        invisibleItems.putFields(items);
    }

    @Override
    public void removeItem(int slot) {
        invisibleItems.removeField(slot);
    }

    @Override
    public GUIPanelItem getItem(int slot) {
        return invisibleItems.getField(slot);
    }

    public boolean canGoNext() {
        return !(getSize() - (18 + (getSize()/9 - 2) * 2) - show.size() > 0);
    }

    public void next(Player player) {
        scrollIndex++;
        setDirty(true);
        getParent().open(player);
    }

    public void next(Player player, int amount) {
        int rows = getSize() / 9 - 2;
        int offset = (scrollIndex + amount) * rows;
        int itemInCenter = rows * 7;

        if ((itemInCenter + offset) - invisibleItems.all().size() > rows) {
            next(player, amount-1);
            return;
        }

        scrollIndex += amount;
        setDirty(true);
        getParent().open(player);
    }

    public boolean canGoBack() {
        return !(getSize() - (18 + (getSize()/9 - 2) * 2) - show.size() > 0);
    }

    public void back(Player player) {
        if (scrollIndex > 0) {
            scrollIndex--;
            setDirty(true);
            getParent().open(player);
            return;
        }
    }

    public void back(Player player, int amount) {
        if (scrollIndex - amount < 0) scrollIndex = 0;
        else scrollIndex -= amount;
        setDirty(true);
        getParent().open(player);
    }
}
