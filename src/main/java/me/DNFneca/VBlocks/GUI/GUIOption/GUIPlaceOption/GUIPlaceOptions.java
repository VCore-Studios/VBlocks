package me.DNFneca.VBlocks.GUI.GUIOption.GUIPlaceOption;

import me.DNFneca.VBlocks.GUI.GUI.Base.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import me.DNFneca.VBlocks.GUI.GUIItem.GUIItem;
import me.DNFneca.VBlocks.Manager.RegistryManager;
import me.DNFneca.VBlocks.Registry.Registry;
import me.DNFneca.VBlocks.Registry.RegistryReference;
import me.DNFneca.VBlocks.Util.ComponentUtils;

import java.util.List;

public class GUIPlaceOptions {
    public static final Registry<GUIPlaceOption> GUIPlaceOptions = RegistryManager.createRegistry("GUIPlaceOptions");

    private static String temp;

    public static final RegistryReference<GUIPlaceOption> SHOULD_PLACE_BACK =
            GUIPlaceOptions.register("should_place_back",
                    new GUIPlaceOption()
                            .setPlaceFunction(gui ->
                            {
                                if (!gui.canGoBack()) return null;
                                return gui.getSize() - 9;
                            })
                            .setGuiItem(new GUIItem(Material.ARROW, "Back")
                                    .addOnClick((gui) -> gui.back((Player) gui.getInventory().getViewers().getFirst()))
                            )
                            .setVisible(true)
                            .build()
            );

    public static final RegistryReference<GUIPlaceOption> SHOULD_PLACE_EXIT =
            GUIPlaceOptions.register("should_place_exit",
                    new GUIPlaceOption()
                            .setPlaceFunction(gui -> gui.getSize() - 5)
                            .setGuiItem(new GUIItem(Material.ARROW, "Exit")
                                    .addOnClick(GUI::closeGUI)
                            )
                            .setVisible(true)
                            .build()
            );

    public static final RegistryReference<GUIPlaceOption> SHOULD_PLACE_NEXT =
            GUIPlaceOptions.register("should_place_next",
                    new GUIPlaceOption()
                            .setPlaceFunction(gui -> {
                                if (!gui.canGoNext()) return null;
                                return gui.getSize() - 1;
                            })
                            .setGuiItem(new GUIItem(Material.ARROW, "Back")
                                    .addOnClick((gui) -> gui.next((Player) gui.getInventory().getViewers().getFirst()))
                            )
                            .setVisible(true)
                            .build()
            );

    public static final RegistryReference<GUIPlaceOption> SHOULD_PLACE_BACK_TO_DIFFERENT_MENU =
            GUIPlaceOptions.register("should_place_return",
                    new GUIPlaceOption()
                            .setPlaceFunction(gui -> {
                                GUI differentGUI = GUI.findFirstDifferentParent(gui);
                                if (differentGUI != null) {
                                    temp = ComponentUtils.serializeComponentAsString(GUI.findFirstDifferentParent(gui).getTitle());
                                    return gui.getSize() - 5;
                                }
                                return null;
                            })
                            .setGuiItem(new GUIItem(Material.ARROW, "Return to " + temp)
                                    .addOnClick((gui) -> gui.back((Player) gui.getInventory().getViewers().getFirst()))
                            )
                            .setVisible(true)
                            .build()
            );

    public static final RegistryReference<GUIPlaceOption> SHOULD_PLACE_SEARCH =
            GUIPlaceOptions.register("should_place_search",
                    new GUIPlaceOption()
                            .setPlaceFunction(gui -> 4)
                            .setGuiItem(new GUIItem(Material.OAK_SIGN, "Search")
                                    .addOnClick((gui) -> SearchSignGUI.openSearch((Player) gui.getInventory().getViewers().getFirst(), s -> {
                                        ListGUI listGUI = new ListGUI(gui.getTitle(), gui.getSize(), List.of(), SearchSignGUI.searchItemsAndBlocks(s));
                                        listGUI.getPlacementOptions().addField(SHOULD_PLACE_BACK);
                                        listGUI.getPlacementOptions().addField(SHOULD_PLACE_NEXT);
                                        listGUI.getPlacementOptions().addField(SHOULD_PLACE_BACK_TO_DIFFERENT_MENU);
                                        listGUI.getPlacementOptions().removeField(GUIPlaceOptions.getReference("should_place_search"));
                                        listGUI.setParentGUI(gui);
                                        listGUI.open((Player) gui.getInventory().getViewers().getFirst());
                                        return;
                                    }))
                            )
                            .setVisible(true)
                            .build()
            );

    public static final RegistryReference<GUIPlaceOption> SCROLLABLE_HORIZONTAL_BACK =
            GUIPlaceOptions.register("scrollable_horizontal_back",
                    new GUIPlaceOption()
                            .setPlaceFunction(gui -> gui.getSize() - 9)
                            .setGuiItem(new GUIItem(Material.ARROW, "Back")
                                    .addOnClick((gui) -> gui.back((Player) gui.getInventory().getViewers().getFirst()), ClickType.UNKNOWN)
                                    .addOnClick((gui) -> {
                                        if (!(gui instanceof ScrollableHorizontalGUI scrollableHorizontalGUI)) return;
                                        scrollableHorizontalGUI.back((Player) scrollableHorizontalGUI.getInventory().getViewers().getFirst(), 7);
                                    }, ClickType.SHIFT_LEFT))
                            .setVisible(true)
                            .build()
            );

    public static final RegistryReference<GUIPlaceOption> SCROLLABLE_HORIZONTAL_NEXT =
            GUIPlaceOptions.register("scrollable_horizontal_next",
                    new GUIPlaceOption()
                            .setPlaceFunction(gui -> gui.getSize() - 1)
                            .setGuiItem(new GUIItem(Material.ARROW, "Next")
                                    .addOnClick((gui) -> gui.next((Player) gui.getInventory().getViewers().getFirst()), ClickType.UNKNOWN)
                                    .addOnClick((gui) -> {
                                        if (!(gui instanceof ScrollableHorizontalGUI scrollableHorizontalGUI)) return;
                                        scrollableHorizontalGUI.next((Player) scrollableHorizontalGUI.getInventory().getViewers().getFirst(), 7);
                                    }, ClickType.SHIFT_LEFT))
                            .setVisible(true)
                            .build()
            );

    public static final RegistryReference<GUIPlaceOption> SCROLLABLE_VERTICAL_BACK =
            GUIPlaceOptions.register("scrollable_vertical_back",
                    new GUIPlaceOption()
                            .setPlaceFunction(gui -> gui.getSize() - 1)
                            .setGuiItem(new GUIItem(Material.ARROW, "Up")
                                    .addOnClick((gui) -> gui.back((Player) gui.getInventory().getViewers().getFirst()), ClickType.UNKNOWN)
                                    .addOnClick((gui) -> {
                                        if (!(gui instanceof ScrollableVerticalGUI scrollableVerticalGUI)) return;
                                        scrollableVerticalGUI.back((Player) scrollableVerticalGUI.getInventory().getViewers().getFirst(), 7);
                                    }, ClickType.SHIFT_LEFT))
                            .setVisible(true)
                            .build()
            );

    public static final RegistryReference<GUIPlaceOption> SCROLLABLE_VERTICAL_NEXT =
            GUIPlaceOptions.register("scrollable_vertical_next",
                    new GUIPlaceOption()
                            .setPlaceFunction(gui -> 8)
                            .setGuiItem(new GUIItem(Material.ARROW, "Down")
                                    .addOnClick((gui) -> gui.next((Player) gui.getInventory().getViewers().getFirst()), ClickType.UNKNOWN)
                                    .addOnClick((gui) -> {
                                        if (!(gui instanceof ScrollableVerticalGUI scrollableVerticalGUI)) return;
                                        scrollableVerticalGUI.next((Player) scrollableVerticalGUI.getInventory().getViewers().getFirst(), 7);
                                    }, ClickType.SHIFT_LEFT))
                            .setVisible(true)
                            .build()
            );
}

