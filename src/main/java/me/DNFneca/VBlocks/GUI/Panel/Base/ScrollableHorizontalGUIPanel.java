package me.DNFneca.VBlocks.GUI.Panel.Base;

// TODO: finish the panel system

import me.DNFneca.VBlocks.GUI.GUI.Base.PanelGUI;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelField.GUIPanelItems;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelItem.GUIPanelItem;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelOption.GUIPanelPlaceOption.GUIPanelPlaceOptions;
import me.DNFneca.VBlocks.VBlocks;
import org.bukkit.Material;
import org.bukkit.entity.Player;


public class ScrollableHorizontalGUIPanel extends ScrollableGUIPanel {


    public ScrollableHorizontalGUIPanel(PanelGUI parent) {
        super(parent);
        getPlacementOptions().addField(GUIPanelPlaceOptions.SCROLLABLE_HORIZONTAL_NEXT);
        getPlacementOptions().addField(GUIPanelPlaceOptions.SCROLLABLE_HORIZONTAL_BACK);
    }

    @Override
    public void build(Player player) {
        int paddingLeft = getGuiOptions().getPadding_left();
        int paddingRight = getGuiOptions().getPadding_right();
        int paddingTop = getGuiOptions().getPadding_top();
        int paddingBottom = getGuiOptions().getPadding_bottom();
        int usableHeight = getHeight() - (paddingTop + paddingBottom);
        int verticalPadding = paddingBottom + paddingTop;
        int horizontalPadding = paddingLeft + paddingRight;
        int offset = getScrollIndex() * (getHeight() - (paddingTop + paddingBottom));
        for (int row = 0; row < getWidth(); row++) {
            for (int column = 0; column < getHeight(); column++) {
                int slot = row + column * getWidth() + column * (9 - getWidth());

                if (row > getWidth() - horizontalPadding || column > getHeight() - verticalPadding || row < paddingLeft || column < paddingTop) {
                    getItems().putField(slot,
                            new GUIPanelItem(
                                    getGuiOptions().getPaddingMaterial(),
                                    ""
                            )
                    );
                    continue;
                }

                int index = offset + (row - paddingLeft) * usableHeight + (column - paddingTop);

                if (getInvisibleItems().containsKey(index)) {
                    getItems().putField(slot, getInvisibleItems().getField(index));
                } else {
                    getItems().removeField(slot);
                }
            }
        }
        super.build(player);
    }
}
