package me.DNFneca.VBlocks.GUI.GUI.Base;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import me.DNFneca.VBlocks.GUI.GUI.GUIOption.GUIPlaceOption.GUIPlaceOption;
import me.DNFneca.VBlocks.GUI.GUI.GUIOption.GUIPlaceOption.GUIPlaceOptions;
import me.DNFneca.VBlocks.Registry.RegistryReference;
import me.DNFneca.VBlocks.Util.ItemUtils;

import java.util.ArrayList;
import java.util.List;

public class ListGUI extends GUI {
    List<ItemStack> show = new ArrayList<>();

    public ListGUI(Component title, int size, List<ItemStack> items) {
        super(title, size);
        this.getPlacementOptions().addField(GUIPlaceOptions.SHOULD_PLACE_EXIT);
        this.getPlacementOptions().addField(GUIPlaceOptions.SHOULD_PLACE_BACK);
        this.getPlacementOptions().addField(GUIPlaceOptions.SHOULD_PLACE_NEXT);
        this.getPlacementOptions().addField(GUIPlaceOptions.SHOULD_PLACE_SEARCH);
        this.getPlacementOptions().addField(GUIPlaceOptions.SHOULD_PLACE_BACK_TO_DIFFERENT_MENU);
        show.addAll(items);
    }

    public ListGUI(Component title, int size, List<RegistryReference<GUIPlaceOption>> options, List<ItemStack> items) {
        super(title, size);
        getPlacementOptions().addFields(options);
        show.addAll(items);
    }

    @Override
    public void open(Player player) {
        int itemCount = 0;
        for (int placementIndex = 10; placementIndex < getSize() - 9; placementIndex++) {
            if (placementIndex % 9 != 0 && placementIndex % 9 != 8 && itemCount < show.size()) {
                if(show.get(itemCount).getType().isItem() && show.get(itemCount).getType() != Material.AIR) {
                    this.getItems().putField(placementIndex, ItemUtils.makeItemGUIItem(show.get(itemCount), ItemUtils.getFriendlyName(show.get(itemCount).getType())));
                }
                itemCount++;
            }
        }
        super.open(player);
    }

    @Override
    public boolean canGoNext() {
        return !(getSize() - (18 + (getSize()/9 - 2) * 2) - show.size() > 0);
    }

    public void next(Player player) {
        ArrayList<ItemStack> items = new ArrayList<>();
        for (int i = getSize() - (18 + (getSize()/9 - 2) * 2); i < show.size(); i++) {
            items.add(show.get(i));
        }
        new ListGUI(getTitle(), getSize(), getPlacementOptions().all(), items).setParentGUI(this).open(player);
    }
}
