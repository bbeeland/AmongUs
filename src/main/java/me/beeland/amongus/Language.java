package me.beeland.amongus;

public enum Language {

    PLAYER_ONLY("Player-Only"),

    LOBBY_CREATED("Lobby-Created"),

    UPLOAD_DATA_NAME("Names.Upload-Data"),
    UPLOAD_DATA_INFO("Info.Upload-Data"),

    TASK_STARTED("Task-Started"),
    TASK_COMPLETED("Task-Completed"),
    TASK_RESTARTED("Task-Restarted"),

    UPLOAD_DATA_MENU_TITLE("Titles.Upload-Data");

    private String path;

    Language(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
