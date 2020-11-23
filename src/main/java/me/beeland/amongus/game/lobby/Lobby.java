package me.beeland.amongus.game.lobby;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.beeland.amongus.AmongUs;
import me.beeland.amongus.arena.Arena;
import me.beeland.amongus.game.GameState;
import me.beeland.amongus.game.LobbySettings;
import me.beeland.amongus.game.Timer;
import me.beeland.amongus.game.session.PlayerSession;
import me.beeland.amongus.game.task.UploadDataTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Lobby {

    private AmongUs plugin;
    private String joinCode;
    private Arena arena;
    private GameState state;
    private HashMap<LobbySettings, String> settings;
    private Timer timer;
    private UUID host;
    private UUID imposter;
    private List<UUID> ingame;
    private List<PlayerSession> playerSessions;

    private HashMap<PlayerSession, UploadDataTask> queue;


    public Lobby(AmongUs plugin, Player host) {

        this.plugin = plugin;
        this.settings = Maps.newHashMap();
        this.timer = new Timer(plugin, this);
        this.host = host.getUniqueId();
        this.ingame = Lists.newArrayList();
        this.playerSessions = Lists.newArrayList();

        for(LobbySettings lobbySetting : LobbySettings.values()) {
            settings.put(lobbySetting, plugin.getConfig().getString("Defaults." + lobbySetting));
        }

        for(Arena arena : plugin.getArenaManager().getArenas()) {

            if(arena.getOwningLobby() == null) {
                arena.setOwningLobby(this);
                this.arena = arena;
                break;
            }

        }

        this.playerSessions.add(new PlayerSession(this, host.getUniqueId()));
        host.teleport(arena.getLobbyLocation());

        timer.runTaskTimerAsynchronously(plugin, 1, 20);

    }

    public String getJoinCode() {
        return joinCode;
    }

    public Arena getArena() {
        return arena;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public HashMap<LobbySettings, String> getSettings() {
        return settings;
    }

    public Timer getTimer() {
        return timer;
    }

    public UUID getHostId() {
        return host;
    }

    public Player getHost() {
        return Bukkit.getPlayer(host);
    }

    public UUID getImposterId() {
        return imposter;
    }

    public Player getImposter() {
        return Bukkit.getPlayer(imposter);
    }

    public List<UUID> getIngame() {
        return ingame;
    }

    public List<PlayerSession> getPlayerSessions() {
        return playerSessions;
    }

    public void addPlayerSession(PlayerSession session) {
        playerSessions.add(session);
    }

    public void removePlayerSession(PlayerSession session) {
        playerSessions.remove(session);
    }

    public PlayerSession getSessionByUUID(UUID uuid) {

        for(PlayerSession session : playerSessions) {
            if(session.getPlayerId().toString().equalsIgnoreCase(uuid.toString())) return session;
        }
        return null;
    }

}
