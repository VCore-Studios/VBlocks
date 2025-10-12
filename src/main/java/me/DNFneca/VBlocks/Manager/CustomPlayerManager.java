package me.DNFneca.VBlocks.Manager;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import me.DNFneca.VBlocks.VBlocks;
import me.DNFneca.VBlocks.CustomPlayer.CustomPlayer;
import me.DNFneca.VBlocks.Registry.SerializerRegistry;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static org.bukkit.Bukkit.getServer;

public class CustomPlayerManager implements Listener {
    private final static CustomPlayerManager INSTANCE = new CustomPlayerManager();
    private final static ConcurrentHashMap<UUID, CustomPlayer> customPlayers = new ConcurrentHashMap<>();
    @Getter
    @Setter
    private Consumer<Player> onPlayerJoin = _ -> {};
    @Getter
    @Setter
    private Consumer<AsyncPlayerPreLoginEvent> onPrePlayerJoin = player -> {
        CustomPlayer customPlayer = load(player.getUniqueId());
        if (customPlayer == null) {
            addCustomPlayer(new CustomPlayer(player.getUniqueId()));
        }
    };
    @Setter
    private Class<? extends CustomPlayer> customPlayerClass = CustomPlayer.class;
    @Setter
    private Consumer<PlayerQuitEvent> onPlayerQuit = event -> removeCustomPlayer(event.getPlayer().getUniqueId());

    private CustomPlayerManager() {}

    public static void init() {
        getServer().getPluginManager().registerEvents(INSTANCE, VBlocks.getInstance());
    }

    public static CustomPlayer getCustomPlayer(UUID uuid) {
        return customPlayers.get(uuid);
    }

    public static void saveAll() {
        customPlayers.keys().asIterator().forEachRemaining(INSTANCE::save);

    }

    public CustomPlayer load(String UUID) {
        try (FileReader reader = new FileReader(VBlocks.getInstance().getDataFolder() + "/" + UUID + ".json")) {
            CustomPlayer customPlayer = SerializerRegistry.getGson().fromJson(reader, customPlayerClass);
            if (customPlayer == null) return null;
            addCustomPlayer(customPlayer);
            return customPlayer;
        } catch (IOException e) {
            return null;
        }
    }

    public CustomPlayer load(UUID UUID) {
        return load(UUID.toString());
    }

    public void save(UUID UUID) {
        try (FileWriter writer = new FileWriter(VBlocks.getInstance().getDataFolder() + "/" + UUID.toString() + ".json")) {
            String json = SerializerRegistry.getGson().toJson(getCustomPlayer(UUID), customPlayerClass);
            writer.write(json);
        } catch (IOException e) {
            return;
        }
    }

    public void addCustomPlayer(CustomPlayer player) {
        if (getCustomPlayer(player.getUUID()) != null) {
            customPlayers.remove(player.getUUID());
        }
        customPlayers.put(player.getUUID(), player);
    }

    public void removeCustomPlayer(UUID player) {
        if (getCustomPlayer(player) != null) {
            save(player);
            customPlayers.remove(player);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        onPlayerJoin.accept(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        onPlayerQuit.accept(event);
    }

    @EventHandler
    public void onPreJoin(AsyncPlayerPreLoginEvent event) {
        onPrePlayerJoin.accept(event);
    }
}
