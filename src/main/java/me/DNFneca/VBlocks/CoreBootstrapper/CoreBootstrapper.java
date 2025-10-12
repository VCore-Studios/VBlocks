package me.DNFneca.VBlocks.CoreBootstrapper;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.EnchantmentKeys;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.EquipmentSlotGroup;
import me.DNFneca.VBlocks.Command.ReloadConfigExecutor;
import me.DNFneca.VBlocks.Command.TestExecutor;

public class CoreBootstrapper implements PluginBootstrap {

    @Override
    public void bootstrap(BootstrapContext context) {
        // Register a new handler for the compose lifecycle event on the enchantment registry
        context.getLifecycleManager().registerEventHandler(RegistryEvents.ENCHANTMENT.compose().newHandler(event ->
                event.registry().register(
                // The key of the registry
                // Plugins should use their own namespace instead of minecraft or papermc
                EnchantmentKeys.create(Key.key("papermc:pointy")),
                b -> b.description(Component.text("Pointy"))
                        .supportedItems(event.getOrCreateTag(ItemTypeTagKeys.SWORDS))
                        .anvilCost(1)
                        .maxLevel(25)
                        .weight(10)
                        .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(1, 1))
                        .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(3, 1))
                        .activeSlots(EquipmentSlotGroup.ANY)
        )));
        context.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register("test", new TestExecutor());
            commands.registrar().register("reloadconfig", new ReloadConfigExecutor());
        });

        context.getLogger().info("Bootstrap complete!");
    }
}