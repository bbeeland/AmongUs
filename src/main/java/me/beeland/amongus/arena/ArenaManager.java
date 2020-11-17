package me.beeland.amongus.arena;

import com.google.common.collect.Lists;
import me.beeland.amongus.AmongUs;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;
import java.util.UUID;

public class ArenaManager {

    private AmongUs plugin;
    private List<Arena> arenas;

    public ArenaManager(AmongUs plugin) {

        this.plugin = plugin;
        this.arenas = Lists.newArrayList();

        FileConfiguration arenaConf =  plugin.getArenaConfig().getConfig();

        for(String id : arenaConf.getKeys(false)) {

            Arena arena = new Arena(plugin, UUID.fromString(id));

            arena.setMeetingLocation(plugin.deserializeLocation(arenaConf.getString(id + ".Meeting")));
            arena.setLobbyLocation(plugin.deserializeLocation(arenaConf.getString(id + ".Lobby")));

            plugin.getLogger().info("Arena Loaded : " + id);
        }


    }

    public void disable() {

        for(Arena arena : getArenas()) arena.save();

    }

    public List<Arena> getArenas() {
        return arenas;
    }

    public void addArena(Arena arena) {
        arenas.add(arena);
    }

    public void removeArena(Arena arena) {
        arenas.remove(arena);
    }

    public Arena getArenaById(UUID id) {

        for(Arena arena : arenas) {
            if(arena.getId().toString().equalsIgnoreCase(id.toString())) return arena;
        }

        return null;
    }

}
