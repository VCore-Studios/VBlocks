package me.DNFneca.VBlocks.GUI.Panel.GUIPanelItem;

import lombok.Getter;
import me.DNFneca.VBlocks.GUI.GUI.Base.GUI;
import me.DNFneca.VBlocks.GUI.Panel.Base.GUIPanel;
import me.DNFneca.VBlocks.Lore.Lore;
import me.DNFneca.VBlocks.Util.ItemUtils;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class GUIPanelItem {
    @Getter @NotNull
    private final ItemStack item;
    @NotNull
    private final Map<@NotNull ClickType, @NotNull GUIPanelItemClickEvent> onClickList = new HashMap<>(0);
    @Getter @NotNull
    private GUIPanelItemOptions options = new GUIPanelItemOptions();

//  TODO: Create a GUIItemBuilder to be able to reload item for GUIs dynamically

    public GUIPanelItem(@NotNull ItemStack item) {
        this.item = item;
    }

    public GUIPanelItem(@NotNull Material material, @NotNull String name) {
        this.item = ItemUtils.makeItemOfType(material, name);
    }

    public Map<ClickType, GUIPanelItemClickEvent> getOnClickList() {
        return Map.copyOf(onClickList);
    }

    public GUIPanelItem setOptions(@NotNull GUIPanelItemOptions options) {
        this.options = options;
        return this;
    }

    public GUIPanelItem addOnClick(GUIPanelItemClickEvent onClick, ClickType clickType) {
        this.onClickList.put(clickType, onClick);
        return this;
    }

    public GUIPanelItem addOnClick(Consumer<GUIPanel> consumer, ClickType clickType) {
        this.onClickList.put(clickType, GUIPanelItemClickEvent.of(consumer));
        return this;
    }

    public GUIPanelItem addOnClick(Consumer<GUIPanel> consumer) {
        this.onClickList.put(ClickType.UNKNOWN, GUIPanelItemClickEvent.of(consumer));
        return this;
    }

    public GUIPanelItem addOnClick(Consumer<GUIPanel> consumer, List<ClickType> clickTypes) {
        for (ClickType clickType : clickTypes) {
            this.onClickList.put(clickType, GUIPanelItemClickEvent.of(consumer));
        }
        return this;
    }

    public GUIPanelItem build() {
        if (this.options.isEnchant()) {
            item.editMeta(item -> item.setEnchantmentGlintOverride(true));
        }
        for (Map.Entry<ClickType, GUIPanelItemClickEvent> entry : this.onClickList.entrySet()) {
            if (entry.getValue().getOptions().isShowShortDescription()) {
                item.lore(new Lore(item.lore()).addEmptyLine().addLoreLines(entry.getValue().getShortDescriptionOfEvent().build()).build());
            }
        }
        return this;
    }
}
