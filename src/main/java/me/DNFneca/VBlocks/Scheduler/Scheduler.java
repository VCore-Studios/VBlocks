package me.DNFneca.VBlocks.Scheduler;

import org.bukkit.Bukkit;
import me.DNFneca.VBlocks.VBlocks;

public final class Scheduler {

    private static boolean isFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static void runTask(Runnable runnable) {
        if (isFolia()) {
            Bukkit.getGlobalRegionScheduler().execute(VBlocks.getInstance(), runnable);
        } else {
            Bukkit.getScheduler().runTask(VBlocks.getInstance(), runnable);
        }
    }

    public static void runTaskAsynchronously(Runnable runnable) {
        if (isFolia()) {
            Bukkit.getAsyncScheduler().runNow(VBlocks.getInstance(), scheduledTask -> runnable.run());
        } else {
            Bukkit.getScheduler().runTaskAsynchronously(VBlocks.getInstance(), runnable);
        }
    }

    public static void runTaskAfter(Runnable runnable, int ticks) {
        if (isFolia()) {
            Bukkit.getGlobalRegionScheduler().runDelayed(VBlocks.getInstance(), scheduledTask -> runnable.run(), ticks);
        } else {
            Bukkit.getScheduler().runTaskLater(VBlocks.getInstance(), runnable, ticks);
        }
    }

}
