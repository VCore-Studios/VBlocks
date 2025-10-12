package me.DNFneca.VBlocks.Manager;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import me.DNFneca.VBlocks.VBlocks;
import me.DNFneca.VBlocks.GUI.GUI.Base.SearchSignGUI;

import java.util.function.Consumer;

public class PacketManager {
    public static ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

    public static void init() {
        protocolManager.addPacketListener(new PacketAdapter(VBlocks.getInstance(), PacketType.Play.Server.OPEN_SIGN_EDITOR) {
            @Override
            public void onPacketSending(PacketEvent packetEvent) {
                VBlocks.logger.info(String.valueOf(packetEvent.getPacket().getBooleans().read(0)));
            }
        });
        protocolManager.addPacketListener(new PacketAdapter(VBlocks.getInstance(), PacketType.Play.Client.UPDATE_SIGN) {
            @Override
            public void onPacketReceiving(PacketEvent packetEvent) {
                Bukkit.getRegionScheduler().run(VBlocks.getInstance(), packetEvent.getPlayer().getLocation(), scheduledTask -> {
                    Player player = packetEvent.getPlayer();

                    // Only process if this player has an active search sign
                    if (!SearchSignGUI.hasActiveSearchSign(player.getUniqueId())) {
                        return; // Ignore regular sign updates
                    }

                    // Clear the active search sign flag since we're processing it

                    BlockPosition blockPosition = new BlockPosition((int) player.getLocation().getX(), (int) player.getLocation().getY() - 2, (int) player.getLocation().getZ());
                    player.sendBlockChange(blockPosition.toLocation(player.getWorld()), player.getWorld().getBlockAt(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ()).getBlockData());

                    Consumer<String> search = SearchSignGUI.getSearch(player);
                    search.accept(packetEvent.getPacket().getStringArrays().read(0)[0]);

                    SearchSignGUI.removePlayer(player.getUniqueId());
                });
            }
        });
    }
}
