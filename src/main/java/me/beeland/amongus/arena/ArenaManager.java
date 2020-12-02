package me.beeland.amongus.arena;

import com.google.common.collect.Lists;
import me.beeland.amongus.AmongUs;
import me.beeland.amongus.game.task.TaskType;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

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
            String lobbyLocationString = arenaConf.getString(id + ".Lobby");
            Location lobbyLoc = lobbyLocationString.equalsIgnoreCase("unset") ? null : plugin.deserializeLocation(lobbyLocationString);

            arena.setLobbyLocation(lobbyLoc);

            if(arenaConf.contains(id + ".Meeting")) {

                for(String string : arenaConf.getStringList(id + ".Meeting")) {
                    arena.addMeetingLocation(plugin.deserializeLocation(string));
                }

            }

            if(arenaConf.contains(id + ".Tasks")) {

                for(String taskTypeString : arenaConf.getConfigurationSection(id + ".Tasks").getKeys(false)) {

                    List<String> taskLocations = arenaConf.getStringList(id + ".Tasks." + taskTypeString);

                    taskLocations.forEach(string -> arena.addTaskLocation(TaskType.valueOf(taskTypeString), plugin.deserializeLocation(string)));
                }

            }

            arenas.add(arena);

            plugin.getLogger().info("Arena Loaded : " + id);
        }


    }

    public void save() {

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

    public Arena getArenaByShortId(String shortId) {

        for(Arena arena : arenas) {
            if(arena.getShortId().equalsIgnoreCase(shortId)) return arena;
        }

        return null;
    }

    public Arena getValidArena() {

        Arena arena = null;

        for(Arena a : arenas) {

            if(a.getLobbyLocation() != null && !a.getMeetingLocations().isEmpty()) {
                arena = a;
            }
        }
        return arena;
    }

}
