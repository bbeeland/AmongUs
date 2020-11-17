package me.beeland.amongus;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PluginConfig {

    private FileConfiguration config;
    private File file;

    public PluginConfig(AmongUs plugin, String name, boolean copy) {

        this.file = new File(plugin.getDataFolder(), name);

        if(!file.exists()) {

            if(copy) {
                plugin.saveResource(name, false);
            } else {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        reload();
    }
    public FileConfiguration getConfig() {
        return config;
    }

    public File getFile() {
        return file;
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }
    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
