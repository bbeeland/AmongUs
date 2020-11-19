package me.beeland.amongus.game;

import org.bukkit.ChatColor;

public enum PlayerTeam {

    WHITE("&fWhite", "&f", ChatColor.WHITE),
    BLACK("&0Black", "&0", ChatColor.BLACK),
    PURPLE("&5Purple", "&5", ChatColor.DARK_PURPLE),
    BLUE("&9Blue", "&9", ChatColor.BLUE),
    DARK_BLUE("&1Dark Blue", "&1", ChatColor.DARK_BLUE),
    AQUA("&bAqua", "&b", ChatColor.AQUA),
    GREEN("&2Green", "&2", ChatColor.DARK_GREEN),
    YELLOW("&eYellow", "&e", ChatColor.YELLOW),
    RED("&cRed", "&c", ChatColor.RED),
    GRAY("&7Grey", "&7", ChatColor.GRAY);

    private String displayName;
    private String chatColor;
    private ChatColor color;

    PlayerTeam(String displayName, String chatColor, ChatColor color) {
        this.displayName = displayName;
        this.chatColor = chatColor;
        this.color = color;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getChatColor() {
        return chatColor;
    }

    public ChatColor getColor() {
        return color;
    }
}
