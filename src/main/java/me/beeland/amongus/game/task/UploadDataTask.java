package me.beeland.amongus.game.task;

import me.beeland.amongus.AmongUs;
import me.beeland.amongus.Language;
import me.beeland.amongus.game.lobby.Lobby;
import me.beeland.amongus.game.session.PlayerSession;

public class UploadDataTask implements CrewTask {

    private AmongUs plugin;

    public UploadDataTask(AmongUs plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return plugin.getLang(Language.UPLOAD_DATA_NAME);
    }

    @Override
    public String getInfo() {
        return plugin.getLang(Language.UPLOAD_DATA_INFO);
    }

    @Override
    public void start(Lobby lobby, PlayerSession session) {

    }

    @Override
    public void complete(Lobby lobby, PlayerSession session) {

    }

    @Override
    public void unload(Lobby lobby, PlayerSession session) {

    }
}
