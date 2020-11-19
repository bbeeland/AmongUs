package me.beeland.amongus.game.task;

import me.beeland.amongus.game.lobby.Lobby;
import me.beeland.amongus.game.session.PlayerSession;

public interface CrewTask {

    String getName();

    String getInfo();

    void start(Lobby lobby, PlayerSession session);

    void complete(Lobby lobby, PlayerSession session);

    void unload(Lobby lobby, PlayerSession session);

}
