package me.DNFneca.VBlocks;

import org.bukkit.plugin.java.JavaPlugin;
import me.DNFneca.VBlocks.Command.ReloadConfigExecutor;
import me.DNFneca.VBlocks.Command.TestExecutor;
import me.DNFneca.VBlocks.Manager.CustomPlayerManager;
import me.DNFneca.VBlocks.Manager.GUIManager;
import me.DNFneca.VBlocks.Manager.PacketManager;
import me.DNFneca.VBlocks.Serializer.NamespacedKeyDeserializer;
import me.DNFneca.VBlocks.Serializer.NamespacedKeySerializer;
import me.DNFneca.VBlocks.Serializer.PlayerOptionDeserializer;
import me.DNFneca.VBlocks.Serializer.PlayerOptionSerializer;

import java.util.logging.Logger;

public final class VBlocks extends JavaPlugin {

    public static Logger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger = getLogger();

        setupAdapters();
        setupCommands();

        this.getDataFolder().mkdirs();

        CustomPlayerManager.init();
        GUIManager.init();

        PacketManager.init();

    }

    @Override
    public void onDisable() {
        CustomPlayerManager.saveAll();
        // Plugin shutdown logic
    }

    public static VBlocks getInstance() {
        return getPlugin(VBlocks.class);
    }

    private void setupCommands() {
        registerCommand("reloadconfig", new ReloadConfigExecutor());
        registerCommand("test", new TestExecutor());
    }

    private void setupAdapters() {
        new PlayerOptionDeserializer();
        new PlayerOptionSerializer();
        new NamespacedKeyDeserializer();
        new NamespacedKeySerializer();
    }
}
