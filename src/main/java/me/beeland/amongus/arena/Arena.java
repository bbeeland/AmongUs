package me.beeland.amongus.arena;

import me.beeland.amongus.AmongUs;
import org.bukkit.Location;

import java.util.UUID;

public class Arena {

    private AmongUs plugin;

    private UUID id;
    private String shortId;
    private Location lobbyLocation;
    private Location meetingLocation;

    public Arena(AmongUs plugin, UUID id) {
        this.plugin = plugin;
        this.id = id;
        this.shortId = id.toString().split("-")[0];

        plugin.getLogger().severe(shortId);
    }

    public UUID getId() {
        return id;
    }

    public String getShortId() {
        return shortId;
    }

    public Location getLobbyLocation() {
        return lobbyLocation;
    }

    public void setLobbyLocation(Location lobbyLocation) {
        this.lobbyLocation = lobbyLocation;
    }

    public Location getMeetingLocation() {
        return meetingLocation;
    }

    public void setMeetingLocation(Location meetingLocation) {
        this.meetingLocation = meetingLocation;
    }

    public void save() {

        String meetingLoc = this.meetingLocation == null ? null : plugin.serializeLocation(meetingLocation);
        String lobbyLoc = this.lobbyLocation == null ? null : plugin.serializeLocation(lobbyLocation);

        plugin.getArenaConfig().getConfig().set(this.id.toString() + ".Meeting", meetingLoc);
        plugin.getArenaConfig().getConfig().set(this.id.toString() + ".Lobby", lobbyLoc);
        plugin.getArenaConfig().save();

    }

}
