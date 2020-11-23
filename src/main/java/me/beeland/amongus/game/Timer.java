package me.beeland.amongus.game;

import me.beeland.amongus.AmongUs;
import me.beeland.amongus.game.lobby.Lobby;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable {

    private AmongUs plugin;
    private Lobby lobby;

    private int[] progressBarSlots = { 10, 11, 12, 13, 14, 15, 16 };

    private int lastUploadUpdate;

    public Timer(AmongUs plugin, Lobby lobby) {

        this.plugin = plugin;
        this.lobby = lobby;

        this.lastUploadUpdate = 0;
    }

    @Override
    public void run() {

        if(lobby.getState() == GameState.LOBBY) return;

    }
}
