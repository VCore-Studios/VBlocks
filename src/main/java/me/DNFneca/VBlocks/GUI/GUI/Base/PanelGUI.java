package me.DNFneca.VBlocks.GUI.GUI.Base;

import lombok.Getter;
import me.DNFneca.VBlocks.GUI.Panel.Base.GUIPanel;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelField.GUIPanelItems;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelItem.GUIPanelItem;
import me.DNFneca.VBlocks.Manager.CustomPlayerManager;
import me.DNFneca.VBlocks.Util.ItemUtils;
import me.DNFneca.VBlocks.VBlocks;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PanelGUI extends GUI {
    @Getter
    private final List<GUIPanel> panels = new ArrayList<>(0);
    @Getter
    private final GUIPanelItems panelItemMap = new GUIPanelItems();
    public PanelGUI(Component title, int size) {
        super(title, size);
    }

    @Override
    public void open(Player player) {
        panels.forEach(guiPanel -> guiPanel.build(player));
        setInventory(Bukkit.createInventory(null, getSize(), getTitle()));
        panelItemMap.all().forEach((key, value) -> {
            if (value != null) {
                getInventory().setItem(key, value.getItem());
            }
        });
        player.openInventory(getInventory());
    }

    @Override
    public void click(int index, ClickType clickType) {
        if (panelItemMap.getField(index) == null || panelItemMap.getField(index).getOnClickList() == null) return;
        for (GUIPanel panel : panels) {
            for (GUIPanelItem guiPanelItem : panel.getItems().all().values()) {
                if (guiPanelItem == panelItemMap.getField(index)) {
                    if (panelItemMap.getField(index).getOnClickList().get(clickType) != null) {
                        panelItemMap.getField(index).getOnClickList().get(clickType).getConsumer().accept(panel);
                    } else if (panelItemMap.getField(index).getOnClickList().get(ClickType.UNKNOWN) != null) {
                        panelItemMap.getField(index).getOnClickList().get(ClickType.UNKNOWN).getConsumer().accept(panel);
                    }
                }
            }
        }
    }
}
