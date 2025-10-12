package me.DNFneca.VBlocks.Config;

import lombok.Getter;

import java.util.HashSet;

public class ConfigFilesManager {
    @Getter
    private static final HashSet<ConfigFile> configFiles = new HashSet<>(0);

    public static void reloadConfigFiles() {
        configFiles.forEach(ConfigFile::reload);
    }
}
