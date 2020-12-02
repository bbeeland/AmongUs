package me.beeland.amongus.game.session;

import me.beeland.amongus.game.PlayerTeam;
import me.beeland.amongus.game.lobby.Lobby;
import me.beeland.amongus.game.task.CrewTask;
import me.beeland.amongus.game.task.TaskType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerSession {

    private Lobby lobby;
    private UUID playerId;
    private HashMap<CrewTask, Boolean> tasks;
    private PlayerTeam playerTeam;

    private TaskType currentTask;
    private int taskStage;

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

    public PlayerTeam getPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerTeam(PlayerTeam playerTeam) {
        this.playerTeam = playerTeam;
    }

    public TaskType getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(TaskType currentTask) {
        this.currentTask = currentTask;
    }

    public int getTaskStage() {
        return taskStage;
    }

    public void setTaskStage(int taskStage) {
        this.taskStage = taskStage;
    }

    public void nextTaskStage() {
        this.taskStage += 1;
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
