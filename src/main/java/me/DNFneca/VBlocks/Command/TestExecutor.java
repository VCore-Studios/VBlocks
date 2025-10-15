package me.DNFneca.VBlocks.Command;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import me.DNFneca.VBlocks.GUI.GUI.Base.PanelGUI;
import me.DNFneca.VBlocks.GUI.GUI.GUIItem.GUIItem;
import me.DNFneca.VBlocks.GUI.Panel.Base.ScrollableHorizontalGUIPanel;
import me.DNFneca.VBlocks.GUI.Panel.GUIPanelItem.GUIPanelItem;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class TestExecutor implements BasicCommand {

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if (commandSourceStack.getExecutor() instanceof Player player && player.isOp()) {

            PanelGUI panelGUI = new PanelGUI(Component.text("Horizontal"), 54);

            ScrollableHorizontalGUIPanel scrollableHorizontalGUIPanel = new ScrollableHorizontalGUIPanel(panelGUI);
            scrollableHorizontalGUIPanel.putItem(0, new GUIPanelItem(Material.STRING, "0"));
            scrollableHorizontalGUIPanel.putItem(2, new GUIPanelItem(Material.STRING, "2"));
            scrollableHorizontalGUIPanel.putItem(4, new GUIPanelItem(Material.STRING, "4"));
            scrollableHorizontalGUIPanel.putItem(6, new GUIPanelItem(Material.STRING, "6"));
            scrollableHorizontalGUIPanel.putItem(8, new GUIPanelItem(Material.STRING, "8"));
            scrollableHorizontalGUIPanel.putItem(153, new GUIPanelItem(Material.STRING, "153"));
            scrollableHorizontalGUIPanel.getGuiOptions().setPadding(1);
            panelGUI.getPanels().add(scrollableHorizontalGUIPanel);

            panelGUI.open(player);

            return;
        }
        commandSourceStack.getExecutor().sendMessage(Component.text("Only oped players can execute this command!"));
    }
}
