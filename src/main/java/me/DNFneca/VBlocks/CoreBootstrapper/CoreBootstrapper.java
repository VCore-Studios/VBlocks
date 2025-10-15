package me.DNFneca.VBlocks.CoreBootstrapper;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.DialogKeys;
import io.papermc.paper.registry.keys.EnchantmentKeys;
import io.papermc.paper.registry.keys.tags.ItemTypeTagKeys;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.inventory.EquipmentSlotGroup;
import me.DNFneca.VBlocks.Command.ReloadConfigExecutor;
import me.DNFneca.VBlocks.Command.TestExecutor;

import java.util.List;

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

        context.getLifecycleManager().registerEventHandler(RegistryEvents.DIALOG.compose()
                .newHandler(event -> event.registry().register(
                        DialogKeys.create(Key.key("papermc:custom_dialog")),
                        builder -> builder
                                .base(DialogBase.builder(Component.text("Title")).build())
                                .type(DialogType.notice())
                )));

        context.getLifecycleManager().registerEventHandler(RegistryEvents.DIALOG.compose(),
                e -> e.registry().register(
                        DialogKeys.create(Key.key("papermc:praise_paperchan")),
                        builder -> builder
                                .base(DialogBase.builder(Component.text("Accept our rules!", NamedTextColor.LIGHT_PURPLE))
                                        .canCloseWithEscape(false)
                                        .body(List.of(
                                                DialogBody.plainMessage(Component.text("By joining our server you agree that Paper-chan is cute!"))
                                        ))
                                        .build()
                                )
                                .type(DialogType.confirmation(
                                        ActionButton.builder(Component.text("Paper-chan is cute!", TextColor.color(0xEDC7FF)))
                                                .tooltip(Component.text("Click to agree!"))
                                                .action(DialogAction.customClick(Key.key("papermc:paperchan/agree"), null))
                                                .build(),
                                        ActionButton.builder(Component.text("I hate Paper-chan!", TextColor.color(0xFF8B8E)))
                                                .tooltip(Component.text("Click this if you are a bad person!"))
                                                .action(DialogAction.customClick(Key.key("papermc:paperchan/disagree"), null))
                                                .build()
                                ))
                )
        );

        context.getLogger().info("Bootstrap complete!");
    }
}