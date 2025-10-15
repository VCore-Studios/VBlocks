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
        int paddingTop = getGuiOptions().getPadding_top();
        int usableWidth = getWidth() - paddingLeft * 2;
        int usableHeight = getHeight() - paddingTop * 2;
        int offset = getScrollIndex() * usableHeight;
        for (int row = 0; row < getWidth() ; row++) {
            for (int column = 0; column < getHeight(); column++) {
                if (row > usableWidth || column > usableHeight) {
                    getItems().putField(row + column * getHeight() + column * (9 - getWidth()),
                            new GUIPanelItem(
                                    Material.TWISTING_VINES,
                                    ""
                            )
                    );
                    continue;
                }
                if (row < paddingLeft || column < paddingTop) {
                    getItems().putField(row + column * getHeight() + column * (9 - getWidth()),
                            new GUIPanelItem(
                                    Material.TWISTING_VINES,
                                    ""
                            )
                    );
                }

                int index = offset + row * usableWidth + column;
                int slot = (row + paddingLeft) + (column + paddingTop) * getWidth() + (column + paddingTop) * (9 - getWidth());

                if (getInvisibleItems().containsKey(index)) {
                    getItems().putField(slot, getInvisibleItems().getField(index));
                } else {
                    getItems().removeField(slot);
                }
                if (row < paddingLeft || column < paddingTop) {
                    getItems().putField(row + column * getHeight() + column * (9 - getWidth()),
                            new GUIPanelItem(
                                    Material.TWISTING_VINES,
                                    ""
                            )
                    );
                }
            }
        }
        super.build(player);
    }
}
