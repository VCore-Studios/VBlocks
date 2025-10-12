package me.DNFneca.VBlocks.Config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import org.bukkit.plugin.Plugin;
import me.DNFneca.VBlocks.Util.Pair;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class ConfigFile {
    private final String CONFIG_FILE;
    private final Properties properties;
    private final Plugin plugin;
    private final Logger logger;
    private boolean isEdited = false;
    private String comment = null;
    private static final Gson gson = new Gson();
    @Getter
    private final ArrayList<Pair<?, ?>> fields = new ArrayList<>(0);
    private static final LoadingCache<Object, String> cache = CacheBuilder.newBuilder()
            .maximumSize(300)
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .build(
                    new CacheLoader<>() {
                        @Override
                        public @NotNull String load(@NotNull Object key) {
                            return gson.toJson(key, key.getClass());
                        }
                    }
                );

    public ConfigFile(@NotNull Plugin plugin, @NotNull String fileName, @Nullable String comment, @NotNull Consumer<ArrayList<Pair<?, ?>>> consumer) {
        this.CONFIG_FILE = plugin.getDataFolder() + "/" + fileName + ".properties";
        if (comment != null) this.comment = comment;
        consumer.accept(this.fields);
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.properties = new Properties();
        initialize();
    }

    private void initialize() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
            logger.info("Created plugin data folder: " + plugin.getDataFolder().getPath());
        }

        File f = new File(CONFIG_FILE);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {}
        }
        ConfigFilesManager.getConfigFiles().add(this);
        load();
    }


    public void save() {
        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            if (!isEdited) return;
            if (comment == null) {
                properties.store(output, "Application Configuration");
                isEdited = false;
                return;
            }
            properties.store(output, comment);
            isEdited = false;
        } catch (IOException e) {
            logger.severe("Could not save config file " + CONFIG_FILE + '\n' + e.getMessage());
        }
    }


    
    public void load() {
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
            for (Pair<?, ?> field : fields) {
                if (!properties.containsKey(new Gson().toJson(field.firstValue()))) {
                    set(field.firstValue(), field.secondValue());
                }
            }
            if (isEdited) {
                save();
            }
        } catch (IOException e) {
            logger.warning("ConfigFile file not found, using defaults");
        }
    }
    
    public boolean isEmpty() {
        return this.properties.isEmpty();
    }
    
    public boolean doesFieldExist(String key) {
        return this.properties.containsKey(key);
    }
    
    public void reload() {
        properties.clear();
        load();
        logger.info("Loaded config files for: " + CONFIG_FILE);
    }

    /**
     * Function works by converting the firstValue to a JSON string and then checking if a secondValue for that firstValue exists.
     *
     * @param key Key of type K
     * @param type Type of the secondValue of T
     * @return Value of type T
     */
    public <K, T> T get(K key, Class<T> type) {
        return gson.fromJson(this.properties.getProperty(cache.getUnchecked(key)), type);
    }

    public <K, T> T get(K key, TypeToken<T> type) {
        return gson.fromJson(this.properties.getProperty(cache.getUnchecked(key)), type.getType());
    }

    public <K, T extends Type> T get(K key, T type) {
        return gson.fromJson(this.properties.getProperty(cache.getUnchecked(key)), type);
    }

    public <K, T> void set(K key, T value) {
        properties.setProperty(gson.toJson(key), gson.toJson(value));
        isEdited = true;
    }
}
