package me.DNFneca.VBlocks.GUI.Panel.GUIPanelOption.GUIPanelPlaceOption;

import me.DNFneca.VBlocks.GUI.GUI.Base.*;
import me.DNFneca.VBlocks.GUI.GUI.GUIItem.GUIItem;
import me.DNFneca.VBlocks.GUI.GUI.GUIOption.GUIPlaceOption.GUIPlaceOptions;
import me.DNFneca.VBlocks.GUI.Panel.Base.GUIPanel;
import me.DNFneca.VBlocks.GUI.Panel.Base.ScrollableGUIPanel;
import me.DNFneca.VBlocks.GUI.Panel.Base.ScrollableHorizontalGUIPanel;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelItem.GUIPanelItem;
import me.DNFneca.VBlocks.Manager.RegistryManager;
import me.DNFneca.VBlocks.Registry.Registry;
import me.DNFneca.VBlocks.Registry.RegistryReference;
import me.DNFneca.VBlocks.VBlocks;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.List;

public class GUIPanelPlaceOptions {
    public static final Registry<GUIPanelPlaceOption> GUIPanelPlaceOptions = RegistryManager.createRegistry("GUIPanelPlaceOptions");

    private static String temp;

    public static final RegistryReference<GUIPanelPlaceOption> SHOULD_PLACE_SEARCH =
            GUIPanelPlaceOptions.register("should_place_search",
                    new GUIPanelPlaceOption()
                            .setPlaceFunction(gui -> 4)
                            .setGuiItem(new GUIPanelItem(Material.OAK_SIGN, "Search")
                                    .addOnClick((gui) -> SearchSignGUI.openSearch((Player) gui.getParent().getInventory().getViewers().getFirst(), s -> {
                                        ListGUI listGUI = new ListGUI(gui.getParent().getTitle(), gui.getSize(), List.of(), SearchSignGUI.searchItemsAndBlocks(s));
                                        listGUI.getPlacementOptions().addField(GUIPlaceOptions.SHOULD_PLACE_BACK);
                                        listGUI.getPlacementOptions().addField(GUIPlaceOptions.SHOULD_PLACE_NEXT);
                                        listGUI.getPlacementOptions().addField(GUIPlaceOptions.SHOULD_PLACE_BACK_TO_DIFFERENT_MENU);
                                        listGUI.getPlacementOptions().removeField(GUIPlaceOptions.GUIPlaceOptions.getReference("should_place_search"));
                                        listGUI.setParentGUI(gui.getParent());
                                        listGUI.open((Player) gui.getParent().getInventory().getViewers().getFirst());
                                        return;
                                    }))
                            )
                            .build()
            );

    public static final RegistryReference<GUIPanelPlaceOption> SCROLLABLE_HORIZONTAL_BACK =
            GUIPanelPlaceOptions.register("scrollable_horizontal_back",
                    new GUIPanelPlaceOption()
                            .setPlaceFunction(gui -> gui.getSize() - gui.getWidth() + 1)
                            .setGuiItem(new GUIPanelItem(Material.ARROW, "Back")
                                    .addOnClick((gui) -> {
                                        if (!(gui instanceof ScrollableGUIPanel scrollableGUIPanel)) return;
                                        scrollableGUIPanel.back((Player) gui.getParent().getInventory().getViewers().getFirst());
                                    }, ClickType.UNKNOWN)
                                    .addOnClick((gui) -> {
                                        if (!(gui instanceof ScrollableGUIPanel scrollableGUIPanel)) return;
                                        scrollableGUIPanel.back((Player) scrollableGUIPanel.getParent().getInventory().getViewers().getFirst(), 7);
                                    }, ClickType.SHIFT_LEFT))
                            .build()
            );

    public static final RegistryReference<GUIPanelPlaceOption> SCROLLABLE_HORIZONTAL_NEXT =
            GUIPanelPlaceOptions.register("scrollable_horizontal_next",
                    new GUIPanelPlaceOption()
                            .setPlaceFunction(GUIPanel::getSize)
                            .setGuiItem(new GUIPanelItem(Material.ARROW, "Next")
                                    .addOnClick((gui) -> {
                                        if (!(gui instanceof ScrollableGUIPanel scrollableGUIPanel)) return;
                                        scrollableGUIPanel.next((Player) scrollableGUIPanel.getParent().getInventory().getViewers().getFirst());
                                    }, ClickType.UNKNOWN)
                                    .addOnClick((gui) -> {
                                        if (!(gui instanceof ScrollableGUIPanel scrollableGUIPanel)) return;
                                        scrollableGUIPanel.next((Player) scrollableGUIPanel.getParent().getInventory().getViewers().getFirst(), 7);
                                    }, ClickType.SHIFT_LEFT))
                            .build()
            );

//    public static final RegistryReference<GUIPanelPlaceOption> SCROLLABLE_VERTICAL_BACK =
//            GUIPanelPlaceOptions.register("scrollable_vertical_back",
//                    new GUIPanelPlaceOption()
//                            .setPlaceFunction(gui -> gui.getSize() - 1)
//                            .setGuiItem(new GUIItem(Material.ARROW, "Up")
//                                    .addOnClick((gui) -> gui.back((Player) gui.getInventory().getViewers().getFirst()), ClickType.UNKNOWN)
//                                    .addOnClick((gui) -> {
//                                        if (!(gui instanceof ScrollableVerticalGUI scrollableVerticalGUI)) return;
//                                        scrollableVerticalGUI.back((Player) scrollableVerticalGUI.getInventory().getViewers().getFirst(), 7);
//                                    }, ClickType.SHIFT_LEFT))
//                            .setVisible(true)
//                            .build()
//            );
//
//    public static final RegistryReference<GUIPanelPlaceOption> SCROLLABLE_VERTICAL_NEXT =
//            GUIPanelPlaceOptions.register("scrollable_vertical_next",
//                    new GUIPanelPlaceOption()
//                            .setPlaceFunction(gui -> 8)
//                            .setGuiItem(new GUIItem(Material.ARROW, "Down")
//                                    .addOnClick((gui) -> gui.next((Player) gui.getInventory().getViewers().getFirst()), ClickType.UNKNOWN)
//                                    .addOnClick((gui) -> {
//                                        if (!(gui instanceof ScrollableVerticalGUI scrollableVerticalGUI)) return;
//                                        scrollableVerticalGUI.next((Player) scrollableVerticalGUI.getInventory().getViewers().getFirst(), 7);
//                                    }, ClickType.SHIFT_LEFT))
//                            .setVisible(true)
//                            .build()
//            );
}
