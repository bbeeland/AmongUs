package me.beeland.amongus;

import me.beeland.amongus.arena.Arena;
import me.beeland.amongus.arena.ArenaManager;
import me.beeland.amongus.commands.AmongUsAdminCommand;
import me.beeland.amongus.game.LobbyManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class AmongUs extends JavaPlugin {

    private PluginConfig mainConfig;
    private PluginConfig langConfig;
    private PluginConfig arenaConfig;

    private ArenaManager arenaManager;
    private LobbyManager lobbyManager;

    @Override
    public void onEnable() {

        this.mainConfig = new PluginConfig(this, "config.yml", true);
        this.langConfig = new PluginConfig(this, "lang.yml", true);
        this.arenaConfig = new PluginConfig(this, "arenas.yml", false);
        this.arenaManager = new ArenaManager();
        this.lobbyManager = new LobbyManager();

        registerCommand("amongusadmin", new AmongUsAdminCommand(this), true);

        for(String string : mainConfig.getConfig().getConfigurationSection("Lobbies").getKeys(false)) {

            Arena arena = new Arena(string);

            arena.setLobbyLocation(deserializeLocation(mainConfig.getConfig().getString("Lobbies." + string + ".Lobby")));
            arena.setMeetingLocation(deserializeLocation(mainConfig.getConfig().getString("Lobbies." + string + ".Meeting")));

            arenaManager.addArena(arena);

            getLogger().info("Arena Loaded -> " + string);

        }

    }

    public PluginConfig getMainConfig() {
        return mainConfig;
    }

    public PluginConfig getLangConfig() {
        return langConfig;
    }

    public PluginConfig getArenaConfig() {
        return arenaConfig;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public LobbyManager getLobbyManager() {
        return lobbyManager;
    }

    public String serializeLocation(Location location) {

        if(location == null) return null;

        StringBuilder builder = new StringBuilder();

        builder.append(location.getWorld().getName()).append("+")
                .append(location.getX()).append("+")
                .append(location.getY()).append("+")
                .append(location.getZ()).append("+")
                .append(location.getYaw()).append("+")
                .append(location.getPitch()).append("+");

        return  builder.toString();
    }

    public Location deserializeLocation(String serializedLocation) {

        String[] split = serializedLocation.split("/+");

        World world = Bukkit.getWorld(split[0]);
        double x = Double.parseDouble(split[1]);
        double y = Double.parseDouble(split[2]);
        double z = Double.parseDouble(split[3]);
        float yaw = Float.parseFloat(split[4]);
        float pitch = Float.parseFloat(split[5]);

        return new Location(world, x, y, z, yaw, pitch);
    }

    private void registerCommand(String name, CommandExecutor commandExecutor, boolean useTabCompletion) {

        PluginCommand command = getCommand(name);

        command.setExecutor(commandExecutor);

        if(useTabCompletion) {
            command.setTabCompleter((TabExecutor) commandExecutor);
        }

    }

}
