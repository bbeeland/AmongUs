package me.beeland.amongus;

public enum Language {

    PLAYER_ONLY("Player-Only"),

    LOBBY_CREATED("Lobby-Created"),

    UPLOAD_DATA_NAME("Names.Upload-Data"),
    UPLOAD_DATA_INFO("Info.Upload-Data");

    private String path;

    Language(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
