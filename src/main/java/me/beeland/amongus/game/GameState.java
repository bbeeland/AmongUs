package me.beeland.amongus.game;

public enum GameState {

    LOBBY(true, true),
    STARTING(true, true),
    IN_GAME(false, false),
    ENDING(false, true),
    VOTING(false, true);


    private boolean joinable, chat;

    GameState(boolean joinable, boolean chat) {
        this.joinable = joinable;
        this.chat = chat;
    }

    public boolean isJoinable() {
        return joinable;
    }

    public boolean isChat() {
        return chat;
    }
}
