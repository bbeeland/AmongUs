package me.beeland.amongus.arena;

import me.beeland.amongus.AmongUs;
import org.bukkit.Location;

import java.util.Random;
import java.util.UUID;

public class Arena {

    private String id;
    private Location lobbyLocation;
    private Location meetingLocation;

    public Arena(String id) {

        this.id = id == null ? UUID.randomUUID().toString().split("-")[0] : id;
    }

    public String getId() {
        return id;
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
}
