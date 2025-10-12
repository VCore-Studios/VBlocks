package me.DNFneca.VBlocks.Util;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SearchUtils {
    public static List<ItemStack> searchFromProvidedItems(String search, List<ItemStack> items) {
        List<ItemStack> result = new ArrayList<>();
        for (ItemStack item : items) {
            if (item.getType().name().toLowerCase().contains(search.toLowerCase())) {
                result.add(item);
            }
        }
        return result;
    }
}
