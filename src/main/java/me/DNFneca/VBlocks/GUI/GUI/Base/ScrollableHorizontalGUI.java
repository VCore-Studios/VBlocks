package me.DNFneca.VBlocks.GUI.GUI.Base;

import lombok.Getter;
import lombok.Setter;
import me.DNFneca.VBlocks.VBlocks;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import me.DNFneca.VBlocks.GUI.GUIItem.GUIItem;
import me.DNFneca.VBlocks.GUI.GUIOption.GUIPlaceOption.GUIPlaceOption;
import me.DNFneca.VBlocks.Registry.RegistryReference;
import org.bukkit.event.inventory.ClickType;

import java.util.*;

import static me.DNFneca.VBlocks.GUI.GUIOption.GUIPlaceOption.GUIPlaceOptions.*;

public class ScrollableHorizontalGUI extends GUI {
    @Getter
    @Setter
    private int scrollIndex = 0;
    @Getter
    private final Map<Integer, GUIItem> visibleItems = new HashMap<>(0);



    public ScrollableHorizontalGUI(Component title, int size, Map<Integer, GUIItem> pattern, List<RegistryReference<GUIPlaceOption>> placementOptions) {
        super(title, size);
        getPlacementOptions().addFields(placementOptions);
        visibleItems.putAll(pattern);

//        Optional<Integer> largestKeyOptional = visibleItems.keySet().stream()
//                .max(Integer::compareTo);
//
//        largestKeyOptional.ifPresent(largestIndex -> {
//            scrollIndex = (largestIndex - 5 * 7) / (getSize() / 9);
//        });
    }

    public ScrollableHorizontalGUI(Component title, int size, Map<Integer, GUIItem> pattern) {
        super(title, size);
        getPlacementOptions().addField(SHOULD_PLACE_EXIT);
        getPlacementOptions().addField(SCROLLABLE_HORIZONTAL_NEXT);
        getPlacementOptions().addField(SCROLLABLE_HORIZONTAL_BACK);
        getPlacementOptions().addField(SHOULD_PLACE_BACK_TO_DIFFERENT_MENU);
        visibleItems.putAll(pattern);
    }

//    FIXME

    @Override
    public void open(Player player) {
        int columns = getSize() / 9 - 1;
        int offset = scrollIndex * columns;
        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < columns; column++) {
                int index = offset + ((row + 1) * 7 - column);
                int slot = ((row + 1) * 7 - column) + row * 2;

                if (visibleItems.containsKey(index)) {
                    this.getItems().putField(slot, visibleItems.get(index));
                } else {
                    this.getItems().removeField(slot);
                }
            }
        }
        super.open(player);
    }

    @Override
    public void next(Player player) {
        scrollIndex++;
        open(player);
    }

    public void next(Player player, int amount) {
        int rows = getSize() / 9 - 2;
        int offset = (scrollIndex + amount) * rows;
        int itemInCenter = rows * 7;

        if ((itemInCenter + offset) - visibleItems.size() > rows) {
            next(player, amount-1);
            return;
        }

        scrollIndex += amount;
        open(player);
    }


    @Override
    public void back(Player player) {
        if (scrollIndex > 0) {
            scrollIndex--;
            open(player);
            return;
        }
        super.back(player);
    }

    public void back(Player player, int amount) {
        if (scrollIndex - amount < 0) scrollIndex = 0;
        else scrollIndex -= amount;
        open(player);
    }
}
