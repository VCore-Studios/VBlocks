package me.DNFneca.VBlocks.GUI.GUI.GUIItem;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import me.DNFneca.VBlocks.GUI.GUI.Base.GUI;
import me.DNFneca.VBlocks.Lore.Lore;
import me.DNFneca.VBlocks.Util.ItemUtils;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class GUIItem {
    @Getter @NotNull
    private final ItemStack item;
    @NotNull
    private final Map<@NotNull ClickType, @NotNull GUIItemClickEvent> onClickList = new HashMap<>(0);
    @Getter @NotNull
    private GUIItemOptions options = new GUIItemOptions();

//  TODO: Create a GUIItemBuilder to be able to reload item for GUIs dynamically

    public GUIItem(@NotNull ItemStack item) {
        this.item = item;
    }

    public GUIItem(@NotNull Material material, @NotNull String name) {
        this.item = ItemUtils.makeItemOfType(material, name);
    }

    public Map<ClickType, GUIItemClickEvent> getOnClickList() {
        return Map.copyOf(onClickList);
    }

    public GUIItem setOptions(@NotNull GUIItemOptions options) {
        this.options = options;
        return this;
    }

    public GUIItem addOnClick(GUIItemClickEvent onClick, ClickType clickType) {
        this.onClickList.put(clickType, onClick);
        return this;
    }

    public GUIItem addOnClick(Consumer<GUI> consumer, ClickType clickType) {
        this.onClickList.put(clickType, GUIItemClickEvent.of(consumer));
        return this;
    }

    public GUIItem addOnClick(Consumer<GUI> consumer) {
        this.onClickList.put(ClickType.UNKNOWN, GUIItemClickEvent.of(consumer));
        return this;
    }

    public GUIItem addOnClick(Consumer<GUI> consumer, List<ClickType> clickTypes) {
        for (ClickType clickType : clickTypes) {
            this.onClickList.put(clickType, GUIItemClickEvent.of(consumer));
        }
        return this;
    }

    public GUIItem build() {
        if (this.options.isEnchant()) {
            item.editMeta(item -> item.setEnchantmentGlintOverride(true));
        }
        for (Map.Entry<ClickType, GUIItemClickEvent> entry : this.onClickList.entrySet()) {
            if (entry.getValue().getOptions().isShowShortDescription()) {
                item.lore(new Lore(item.lore()).addEmptyLine().addLoreLines(entry.getValue().getShortDescriptionOfEvent().build()).build());
            }
        }
        return this;
    }
}
