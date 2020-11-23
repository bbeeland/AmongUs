package me.beeland.amongus.game.lobby;

import com.google.common.collect.Lists;

import java.util.List;

public class LobbyManager {

    private List<Lobby> lobbies;

    public LobbyManager() {
        this.lobbies = Lists.newArrayList();
    }

    public List<Lobby> getLobbies() {
        return lobbies;
    }

    public void addLobby(Lobby lobby) {
        this.lobbies.add(lobby);
    }

    public void removeLobby(Lobby lobby) {
        this.lobbies.remove(lobby);
    }

}
