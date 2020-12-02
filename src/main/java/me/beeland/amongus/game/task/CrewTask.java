package me.beeland.amongus.game.task;

import me.beeland.amongus.game.lobby.Lobby;
import me.beeland.amongus.game.session.PlayerSession;
import org.graalvm.compiler.core.aarch64.AArch64AddressLoweringByUse;

public interface CrewTask {

    String getName();

    String getInfo();

    TaskType getTaskType();

    void start(Lobby lobby, PlayerSession session);

    void complete(Lobby lobby, PlayerSession session);

    void unload(Lobby lobby, PlayerSession session);

}
