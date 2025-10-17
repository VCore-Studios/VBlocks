package me.DNFneca.VBlocks.GUI.Panel.Base;

import lombok.Getter;
import lombok.Setter;
import me.DNFneca.VBlocks.GUI.GUI.Base.PanelGUI;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelField.GUIPanelItems;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelItem.GUIPanelItem;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelOption.GUIPanelPlaceOption.GUIPanelPlaceOptions;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;

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
        Optional<Integer> largestKeyOptional = invisibleItems.all().keySet().stream()
                .max(Integer::compareTo);

        if (largestKeyOptional.isEmpty()) return false;

        int usableWidth = getWidth() - getGuiOptions().getPadding_left() - getGuiOptions().getPadding_right();
        int usableHeight = getHeight() - getGuiOptions().getPadding_top() - getGuiOptions().getPadding_bottom();

        int totalItems = largestKeyOptional.get() + 1;
        int visibleCount = usableWidth * usableHeight;

        int maxScrollIndex = Math.max(0, (int) Math.ceil((double) (totalItems - visibleCount) / usableHeight));

        return scrollIndex < maxScrollIndex;
    }

    public void next(Player player) {
        scrollIndex++;
        setDirty(true);
        getParent().open(player);
    }

    public void next(Player player, int amount) {

        Optional<Integer> largestKeyOptional = invisibleItems.all().keySet().stream()
                .max(Integer::compareTo);

        if (largestKeyOptional.isEmpty()) return;

        int usableWidth = getWidth() - getGuiOptions().getPadding_left() - getGuiOptions().getPadding_right();
        int usableHeight = getHeight() - getGuiOptions().getPadding_top() - getGuiOptions().getPadding_bottom();

        int totalItems = largestKeyOptional.get() + 1;
        int visibleCount = usableWidth * usableHeight;

        int maxScrollIndex = Math.max(0, (int) Math.ceil((double) (totalItems - visibleCount) / usableHeight));

        scrollIndex = Math.min(maxScrollIndex, scrollIndex + amount);
        setDirty(true);
        getParent().open(player);
    }
//
//    public boolean canGoBack() {
//        return !(getSize() - (18 + (getSize()/9 - 2) * 2) - show.size() > 0);
//    }

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
