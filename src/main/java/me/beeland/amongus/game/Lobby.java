package me.beeland.amongus.game;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.beeland.amongus.AmongUs;
import me.beeland.amongus.arena.Arena;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class Lobby {

    private AmongUs plugin;
    private String joinCode;
    private Arena arena;
    private HashMap<LobbySettings, String> settings;
    private Timer timer;
    private String host;
    private String imposter;
    private List<String> ingame;


    public Lobby(AmongUs plugin, Player host) {

        this.plugin = plugin;
        this.settings = Maps.newHashMap();
        this.timer = new Timer(plugin, this);
        this.host = host.getName();
        this.ingame = Lists.newArrayList();

        for(LobbySettings lobbySetting : LobbySettings.values()) {
            settings.put(lobbySetting, plugin.getConfig().getString("Defaults." + lobbySetting));
        }

    }

    public String getJoinCode() {
        return joinCode;
    }

    public Arena getArena() {
        return arena;
    }

    public HashMap<LobbySettings, String> getSettings() {
        return settings;
    }

    public Timer getTimer() {
        return timer;
    }

    public String getHost() {
        return host;
    }

    public String getImposter() {
        return imposter;
    }

    public List<String> getIngame() {
        return ingame;
    }
}
