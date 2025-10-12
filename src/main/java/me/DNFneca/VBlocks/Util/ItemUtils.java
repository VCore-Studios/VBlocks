package me.DNFneca.VBlocks.Util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.DNFneca.VBlocks.GUI.GUIItem.GUIItem;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ItemUtils {

    public static boolean isGUIItemWithSpecificName(ItemStack item, String name) {
        if(item.getItemMeta() == null || !item.getItemMeta().hasDisplayName()) return false;
        ItemMeta meta = item.getItemMeta();
        return meta.displayName().equals(Component.text(name).color(TextColor.fromHexString("#ffffff")).decoration(TextDecoration.ITALIC, false));
    }

    public static GUIItem makeItemGUIItem(ItemStack item, String name) {
        if(item == null || name == null || item.getItemMeta() == null) return null;
        createItem(item, name);
        return new GUIItem(item);
    }

    private static void createItem(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name).color(TextColor.fromHexString("#ffffff")).decoration(TextDecoration.ITALIC, false));
        meta.addItemFlags(ItemFlag.values());
        meta.lore(new ArrayList<>());
        item.setItemMeta(meta);
    }

    public static GUIItem makeGUIItemOfType(Material material, String name) {
        return makeItemGUIItem(new ItemStack(material), name);
    }

    public static GUIItem makeGUIItemOfType(Material material) {
        return makeItemGUIItem(new ItemStack(material), getFriendlyName(material));
    }

    public static ItemStack makeItem(@NotNull ItemStack item, @NotNull String name) {
        if(item.getItemMeta() == null) return null;
        createItem(item, name);
        return item;
    }

    public static ItemStack makeItemOfType(Material material, String name) {
        return makeItem(new ItemStack(material), name);
    }

    public static ItemStack makeItemOfType(Material material) {
        return makeItem(new ItemStack(material), getFriendlyName(material));
    }

    public static String getFriendlyName(Material material) {
        if (material == null) return null;
        // Split the enum name by underscores, capitalize each word, and join them
        String[] words = material.name().toLowerCase().split("_");
        StringBuilder friendlyName = new StringBuilder();
        for (String word : words) {
            friendlyName.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
        }
        return friendlyName.toString().trim(); // Remove trailing space
    }
}
