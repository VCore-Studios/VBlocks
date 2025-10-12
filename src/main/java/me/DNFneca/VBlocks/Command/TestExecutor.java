package me.DNFneca.VBlocks.Command;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.DNFneca.VBlocks.GUI.GUI.Base.ScrollableHorizontalGUI;
import me.DNFneca.VBlocks.GUI.GUIItem.GUIItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class TestExecutor implements BasicCommand {

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if (commandSourceStack.getExecutor() instanceof Player player && player.isOp()) {
            new ScrollableHorizontalGUI(Component.text("Horizontal"), 54,
                    Map.of(
                            33, new GUIItem(Material.STRING, "String"),
                            63, new GUIItem(Material.STRING, "String"),
                            93, new GUIItem(Material.STRING, "String"),
                            123, new GUIItem(Material.STRING, "String"),
                            153, new GUIItem(Material.STRING, "String")
                    )
            ).open(player);
            return;
        }
        commandSourceStack.getExecutor().sendMessage(Component.text("Only oped players can execute this command!"));
    }
}
