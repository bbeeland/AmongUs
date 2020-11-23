package me.beeland.amongus.game.session;

import me.beeland.amongus.game.PlayerTeam;
import me.beeland.amongus.game.lobby.Lobby;
import me.beeland.amongus.game.task.CrewTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerSession {

    private Lobby lobby;
    private UUID playerId;
    private HashMap<CrewTask, Boolean> tasks;
    private PlayerTeam playerTeam;

    public PlayerSession(Lobby lobby, UUID playerId) {

        this.lobby = lobby;
        this.playerId = playerId;

    }

    public Lobby getLobby() {
        return lobby;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(playerId);
    }

    public HashMap<CrewTask, Boolean> getTasks() {
        return tasks;
    }

    public void addTask(CrewTask task) {
        tasks.put(task, false);
    }

    public void removeTask(CrewTask task) {
        tasks.remove(task);
    }

    public void completeTask(CrewTask task) {

        if(tasks.containsKey(task)) {
            tasks.remove(task);
            tasks.put(task, true);
        }

//        boolean incomplete = false;

        /*
        TODO: CHECK TO SEE IF ALL TASKS ARE COMPLETED AND END GAME IF THEY ARE!
         */

    }

}
