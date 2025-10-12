package me.DNFneca.VBlocks.Manager;

import lombok.Getter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import me.DNFneca.VBlocks.VBlocks;
import me.DNFneca.VBlocks.GUI.GUI.Base.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

@Getter
public class GUIManager implements Listener {
    private static final GUIManager INSTANCE = new GUIManager();
    private static final ArrayList<GUI> GUIs = new ArrayList<>(0);

    private GUIManager() {}

    public static void init() {
        getServer().getPluginManager().registerEvents(INSTANCE, VBlocks.getInstance());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        List<GUI> relevantGUIs = new ArrayList<>(0);
        for (GUI gui : GUIs) {
            if (gui.getInventory() != null && gui.getInventory().equals(event.getClickedInventory())) {
                relevantGUIs.add(gui);
                event.setCancelled(true);
            }
        }
        for (GUI gui : relevantGUIs) {
            gui.click(event.getSlot(), event.getClick());
        }
    }

    public GUI findGUIById(UUID id) {
        for (GUI baseGUI : GUIs) {
            if (baseGUI.getId().equals(id)) {
                return baseGUI;
            }
        }
        return null;
    }

    public static void addGUI(GUI gui) {
        GUIs.add(gui);
    }

    public static void removeGUI(GUI gui) {
        GUIs.remove(gui);
    }

    public GUI findGUIById(String id) {
        return findGUIById(UUID.fromString(id));
    }
}
