package me.DNFneca.VBlocks.GUI.GUI.Base;

import lombok.Getter;
import me.DNFneca.VBlocks.Registry.RegistryReference;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import me.DNFneca.VBlocks.VBlocks;
import me.DNFneca.VBlocks.GUI.GUIField.GUIItems;
import me.DNFneca.VBlocks.GUI.GUIField.GUIPlacementOptions;
import me.DNFneca.VBlocks.GUI.GUIOption.GUIOptions;
import me.DNFneca.VBlocks.GUI.GUIOption.GUIPlaceOption.GUIPlaceOption;
import me.DNFneca.VBlocks.Manager.CustomPlayerManager;
import me.DNFneca.VBlocks.Manager.GUIManager;
import me.DNFneca.VBlocks.Util.ItemUtils;

import java.util.UUID;

public abstract class GUI {
    @Getter
    private final GUIItems items = new GUIItems();
    @Getter
    private final GUIPlacementOptions placementOptions = new GUIPlacementOptions();
    @Getter
    private final GUIOptions options = new GUIOptions();
    @Getter
    private final Component title;
    @Getter
    private final UUID id = UUID.randomUUID();
    @Getter
    private final int size;
    @Getter
    private GUI parentGUI;
    @Getter
    private GUI childGUI;
    @Getter
    private Inventory inventory;

    public GUI(Component title, int size) {
        this.title = title;
        this.size = size;
        if (size == 0) return;
        GUIManager.addGUI(this);
    }

    public void open(Player player) {
        fillEmptySlots(player);
        placeOptions();
        inventory = Bukkit.createInventory(null, size, title);
        items.all().forEach((key, value) -> {
            if (value != null) {
                inventory.setItem(key, value.getItem());
            }
        });
        player.openInventory(inventory);
    }

    public void closeGUI() {
        removeGUI();
        inventory.getViewers().getFirst().closeInventory();
    }

    private void removeGUI() {
        if (getParentGUI() != null) {
            getParentGUI().removeGUI();
        }
        if (getChildGUI() != null) {
            getChildGUI().removeGUI();
        }
    }

    public void click(int index, ClickType clickType) {
        if (items.getField(index) == null || items.getField(index).getOnClickList() == null) return;
        if (items.getField(index).getOnClickList().get(clickType) != null) {
            items.getField(index).getOnClickList().get(clickType).getConsumer().accept(this);
        } else if (items.getField(index).getOnClickList().get(ClickType.UNKNOWN) != null) {
            items.getField(index).getOnClickList().get(ClickType.UNKNOWN).getConsumer().accept(this);
        }
    }

    private void fillEmptySlots(Player player) {
        if (!options.isShouldSetBackground()) return;
        for (int i = 0; i < size; i++) {
            if (!items.containsKey(i)) {
                items.putField(i, ItemUtils.makeGUIItemOfType(
                        CustomPlayerManager.getCustomPlayer(
                                player.getUniqueId()
                        ).option(new NamespacedKey(VBlocks.getInstance(), "defaultBackGroundMaterial")).getValue(),
                        ""));
            }
        }
    }

    private void placeOption(RegistryReference<GUIPlaceOption> optionReference) {
        GUIPlaceOption option = optionReference.value();
        Integer slot = option.getPlaceFunction().apply(this);
        if (slot == null || slot < 0 || slot > getSize()) return;
        items.putField(slot, option.getGuiItem());
    }

    private void placeOptions() {
        if (placementOptions.isEmpty()) return;
        for (RegistryReference<GUIPlaceOption> placement : placementOptions.getFields()) {
            placeOption(placement);
        }
    }


    public GUI setParentGUI(GUI parentGUI) {
        this.parentGUI = parentGUI;
        return this;
    }

    public GUI setChildGUI(GUI childGUI) {
        this.childGUI = childGUI;
        return this;
    }

    public static GUI findFirstDifferentParent(GUI gui) {
        if (gui == null) return null;

        Class<?> baseClass = gui.getClass();
        GUI current = gui.getParentGUI();

        while (current != null && current.getClass() == baseClass) {
            current = current.getParentGUI();
        }

        return current; // either null or the first different type
    }

    public boolean canGoNext() {
        return this.getChildGUI() != null;
    }

    public void next(Player player) {
        if (this.getChildGUI() == null) return;
        this.getChildGUI().open(player);
    }

    public boolean canGoBack() {
        return this.getParentGUI() != null;
    }

    public void back(Player player) {
        if (this.getParentGUI() == null) return;
        this.getParentGUI().open(player);
    }

//    public void reloadGUI() {
//        open();
//    }
}