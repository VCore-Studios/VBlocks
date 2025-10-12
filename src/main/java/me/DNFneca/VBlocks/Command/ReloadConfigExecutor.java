package me.DNFneca.VBlocks.Command;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import me.DNFneca.VBlocks.Config.ConfigFilesManager;
import me.DNFneca.VBlocks.Scheduler.Scheduler;

public class ReloadConfigExecutor implements BasicCommand {

    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        Scheduler.runTask(ConfigFilesManager::reloadConfigFiles);
    }
}
