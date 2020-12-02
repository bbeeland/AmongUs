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
    public TaskType getTaskType() {
        return TaskType.DATA_UPLOAD;
    }

    @Override
    public void start(Lobby lobby, PlayerSession session) {

        lobby.addDataUploadQueue(session, this);

        session.setCurrentTask(TaskType.DATA_UPLOAD);
        session.setTaskStage(0);

        plugin.send(session.getPlayer(), Language.TASK_STARTED);

    }

    @Override
    public void complete(Lobby lobby, PlayerSession session) {

        plugin.send(session.getPlayer(), Language.TASK_COMPLETED);
        session.completeTask(this);

    }

    @Override
    public void unload(Lobby lobby, PlayerSession session) {

        lobby.getDataUploadQueue().remove(session);

        session.setCurrentTask(null);
        session.setTaskStage(0);

        session.getPlayer().closeInventory();

    }

}
