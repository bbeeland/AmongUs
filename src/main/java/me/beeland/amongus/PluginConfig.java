package me.beeland.amongus;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PluginConfig {

    private AmongUs plugin;
    private File file;
    private FileConfiguration configuration;

    public PluginConfig(AmongUs plugin, String fileName, boolean hasDefault) {

        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), fileName);

        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        if(!this.file.exists()) {

            if(hasDefault) {
                plugin.saveResource(fileName, true);
            } else {
                try {
                    this.file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        reload();
    }

    public FileConfiguration getConfig() {
        return configuration;
    }

    public void reload() {
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getString(String key) {
        return configuration.getString(key);
    }

    public double getDouble(String key) {
        return configuration.getDouble(key);
    }

    public File getFile() {
        return file;
    }

}
