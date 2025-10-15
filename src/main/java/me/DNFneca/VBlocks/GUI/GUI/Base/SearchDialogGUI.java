package me.DNFneca.VBlocks.GUI.GUI.Base;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedRegistrable;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import com.comphenix.protocol.wrappers.nbt.NbtList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.function.Consumer;

import static me.DNFneca.VBlocks.Manager.PacketManager.protocolManager;

public class SearchDialogGUI {
    // Track players who have opened custom search signs
    private static final Set<UUID> playersWithActiveSearchSign = new HashSet<>();
    private static final Map<UUID, Consumer<String>> searchFunctionMap = new HashMap<>(0);


    public static void openSearch(Player player, Consumer<String> searchFunction) {
        // Mark this player as having an active search sign
        playersWithActiveSearchSign.add(player.getUniqueId());
        searchFunctionMap.put(player.getUniqueId(), searchFunction);

        // Define a dummy block position (does not exist in the world)
        BlockPosition blockPosition = new BlockPosition((int) player.getLocation().getX(), (int) player.getLocation().getY() - 2, (int) player.getLocation().getZ());

        // Create the packet

        player.sendBlockChange(blockPosition.toLocation(player.getWorld()), Material.OAK_SIGN.createBlockData());

        PacketContainer signData = protocolManager.createPacket(PacketType.Play.Server.TILE_ENTITY_DATA);

        NbtCompound signNBT = (NbtCompound) signData.getNbtModifier().read(0);
        NbtCompound frontText = NbtFactory.ofCompound("front_text");
        NbtCompound value = NbtFactory.ofCompound("");
        NbtList<String> messages = NbtFactory.ofList("messages");
        messages.add("");
        messages.add("");
        messages.add("Enter search term:");
        messages.add("----------------");
        value.put(messages);
        frontText.put(messages);
        signNBT.put(frontText);

        signData.getBlockPositionModifier().write(0, blockPosition);
        signData.getBlockEntityTypeModifier().write(0, WrappedRegistrable.blockEntityType("sign"));
        signData.getNbtModifier().write(0, signNBT);

        // Send the "open sign editor" packet
        PacketContainer openSignPacket = protocolManager.createPacket(PacketType.Play.Server.OPEN_SIGN_EDITOR);
        openSignPacket.getBlockPositionModifier().write(0, blockPosition);
        openSignPacket.getBooleans().write(0, true);

        protocolManager.sendServerPacket(player, signData);
        protocolManager.sendServerPacket(player, openSignPacket);
    }

    public static Consumer<String> getSearch(Player player) {
        return searchFunctionMap.get(player.getUniqueId());
    }

    public static Consumer<String> getSearch(UUID uuid) {
        return searchFunctionMap.get(uuid);
    }

    public static void removePlayer(Player player) {
        playersWithActiveSearchSign.remove(player.getUniqueId());
        searchFunctionMap.remove(player.getUniqueId());
    }

    public static void removePlayer(UUID uuid) {
        playersWithActiveSearchSign.remove(uuid);
        searchFunctionMap.remove(uuid);
    }

    public static ArrayList<ItemStack> searchItems(String search) {
        ArrayList<ItemStack> materials = new ArrayList<>();
        for (Material material : Material.values()) {
            if (material.isItem() && material != Material.AIR) {
                materials.add(ItemStack.of(material));
            }
        }
        return materials;
    }

    public static ArrayList<ItemStack> searchItemsAndBlocks(String search) {
        ArrayList<ItemStack> materials = new ArrayList<>();
        for (Material material : Material.values()) {
            if ((material.isItem() || (material.isBlock() && material.isItem()))
                    && material != Material.AIR
                    && material.name().toUpperCase().contains(search.toUpperCase())) {
                materials.add(ItemStack.of(material));
            }
        }
        return materials;
    }
//    public static ArrayList<ItemStack> searchMobs(String search) {
//        ArrayList<ItemStack> materials = new ArrayList<>();
//        for (EntityType material : EntityType.values()) {
//            if (material.isAlive() && material != EntityType.PLAYER) {
//                materials.add(ItemUtils.makeGUIItemOfType(Material.NAME_TAG, MobUtils.getFriendlyName(material)));
//            }
//        }
//        return materials;
//    }

    public static ArrayList<ItemStack> searchBlocks(String search) {
        ArrayList<ItemStack> materials = new ArrayList<>();
        for (Material material : Material.values()) {
            if (material.isItem() && material.isBlock() && material != Material.AIR) {
                materials.add(ItemStack.of(material));
            }
        }
        return materials;
    }

    /**
     * Check if a player has an active search sign
     */
    public static boolean hasActiveSearchSign(UUID playerUUID) {
        return playersWithActiveSearchSign.contains(playerUUID);
    }
}
