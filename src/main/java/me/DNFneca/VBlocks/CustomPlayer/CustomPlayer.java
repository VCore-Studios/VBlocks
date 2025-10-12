package me.DNFneca.VBlocks.CustomPlayer;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.*;
import me.DNFneca.VBlocks.VBlocks;
import me.DNFneca.VBlocks.CustomPlayer.Option.PlayerOption;
import me.DNFneca.VBlocks.Registry.SerializerRegistry;

import java.util.*;

public class CustomPlayer {
    @Getter
    @Setter
    private UUID UUID;
    @Getter
    @Setter
    private UUID currentGUI;
    private String name;
//    @Getter
//    @Setter
//    private Map<NamespacedKey, Long> abilitiesCastHistory = new HashMap<>();
    private final Map<String, PlayerOption> playerOptions = new HashMap<>(0);

    public CustomPlayer(UUID UUID) {
        this.UUID = UUID;
        String json = SerializerRegistry.getGson().toJson(Registry.SOUNDS.getKey(Sound.UI_BUTTON_CLICK));
        VBlocks.logger.info(json);
        NamespacedKey namespacedKey = SerializerRegistry.getGson().fromJson(json, NamespacedKey.class);
        VBlocks.logger.info(namespacedKey.toString());
        option(new NamespacedKey(VBlocks.getInstance(), "defaultBackGroundMaterial"), new PlayerOption(Material.LIGHT_GRAY_STAINED_GLASS_PANE, Material.class));
        option(new NamespacedKey(VBlocks.getInstance(), "defaultGUIClickSound"), new PlayerOption(Registry.SOUNDS.getKey(Sound.UI_BUTTON_CLICK), NamespacedKey.class));
    }

    public PlayerOption option(NamespacedKey namespacedKey) {
        return playerOptions.get(namespacedKey.toString());
    }

    public void option(NamespacedKey namespacedKey, PlayerOption playerOption) {
        playerOptions.put(namespacedKey.toString(), playerOption);
    }

    public Component getName() {
        return GsonComponentSerializer.gson().deserialize(name);
    }

    public void setName(Component name) {
        this.name = GsonComponentSerializer.gson().serialize(name);
    }

}