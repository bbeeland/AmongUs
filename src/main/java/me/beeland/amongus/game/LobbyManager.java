package me.beeland.amongus.game;

import com.google.common.collect.Sets;
import me.beeland.amongus.arena.Arena;
import me.beeland.amongus.game.lobby.Lobby;
import org.bukkit.entity.Player;

import java.util.Set;

public class LobbyManager {

    private Set<Lobby> lobbies;

    public LobbyManager() {
        lobbies = Sets.newHashSet();
    }

    public Set<Lobby> getLobbies() {
        return lobbies;
    }

    public void addLobby(Lobby lobby) {
        this.lobbies.add(lobby);
    }

    public void removeLobby(Lobby lobby) {
        this.lobbies.remove(lobby);
    }

    public Lobby getLobbyByPlayer(Player player) {

        for(Lobby lobby : lobbies) {
            if(lobby.getIngame().contains(player.getName())) return lobby;
        }

        return null;
    }

    public Lobby getLobbyByArena(Arena arena) {

        for(Lobby lobby : lobbies) {
            if(lobby.getArena().getId() == arena.getId()) return lobby;
        }

        return null;
    }

    public Lobby getLobbyByJoinCode(String joinCode) {

        for(Lobby lobby : lobbies) {
            if(lobby.getJoinCode().equalsIgnoreCase(joinCode)) return lobby;
        }

        return null;
    }

}
