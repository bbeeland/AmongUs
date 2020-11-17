package me.beeland.amongus;

public enum Language {

    PLAYER_ONLY("Player-Only");

    private String path;

    Language(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
