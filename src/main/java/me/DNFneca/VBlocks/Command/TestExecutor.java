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
import me.DNFneca.VBlocks.VBlocks;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collection;
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

            switch (args.length) {
                case 1:
                    scrollableHorizontalGUIPanel.setWidth(Integer.parseInt(args[0]));
                    break;
                case 2:
                    scrollableHorizontalGUIPanel.setHeight(Integer.parseInt(args[1]));
                    break;
                case 3:
                    scrollableHorizontalGUIPanel.getGuiOptions().setPadding(Integer.parseInt(args[2]));
                    break;
                case 4:
                    scrollableHorizontalGUIPanel.setWidth(Integer.parseInt(args[0]));
                    scrollableHorizontalGUIPanel.setHeight(Integer.parseInt(args[1]));
                    scrollableHorizontalGUIPanel.getGuiOptions().setPadding_left(Integer.parseInt(args[2]));
                    scrollableHorizontalGUIPanel.getGuiOptions().setPadding_right(Integer.parseInt(args[2]));
                    scrollableHorizontalGUIPanel.getGuiOptions().setPadding_top(Integer.parseInt(args[3]));
                    scrollableHorizontalGUIPanel.getGuiOptions().setPadding_bottom(Integer.parseInt(args[3]));
                    break;
                case 6:
                    scrollableHorizontalGUIPanel.setWidth(Integer.parseInt(args[0]));
                    scrollableHorizontalGUIPanel.setHeight(Integer.parseInt(args[1]));
                    scrollableHorizontalGUIPanel.getGuiOptions().setPadding_left(Integer.parseInt(args[2]));
                    scrollableHorizontalGUIPanel.getGuiOptions().setPadding_right(Integer.parseInt(args[3]));
                    scrollableHorizontalGUIPanel.getGuiOptions().setPadding_top(Integer.parseInt(args[4]));
                    scrollableHorizontalGUIPanel.getGuiOptions().setPadding_bottom(Integer.parseInt(args[5]));
                    break;
            }

            panelGUI.getPanels().add(scrollableHorizontalGUIPanel);

            panelGUI.open(player);

            return;
        }
        commandSourceStack.getExecutor().sendMessage(Component.text("Only oped players can execute this command!"));
    }

    @Override
    public Collection<String> suggest(CommandSourceStack commandSourceStack, String[] args) {
        VBlocks.logger.info(String.valueOf(args.length));
        return BasicCommand.super.suggest(commandSourceStack, args);
    }
}
