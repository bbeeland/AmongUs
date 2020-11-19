package me.beeland.amongus.arena;

import com.google.common.collect.Lists;
import me.beeland.amongus.AmongUs;
import org.bukkit.Location;

import java.util.List;
import java.util.UUID;

public class Arena {

    private AmongUs plugin;

    private UUID id;
    private String shortId;
    private Location lobbyLocation;
    private List<Location> meetingLocations;

    public Arena(AmongUs plugin, UUID id) {
        this.plugin = plugin;
        this.id = id;
        this.shortId = id.toString().split("-")[0];
        this.meetingLocations = Lists.newArrayList();
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

    public List<Location> getMeetingLocations() {
        return meetingLocations;
    }

    public void addMeetingLocation(Location location) {
        this.meetingLocations.add(location);
    }

    public void removeMeetingLocation(Location location) {
        this.meetingLocations.remove(location);
    }

    public void save() {

        String lobbyLoc = this.lobbyLocation == null ? "unset" : plugin.serializeLocation(lobbyLocation);

        plugin.getArenaConfig().getConfig().set(this.id.toString() + ".Lobby", lobbyLoc);

        if(!meetingLocations.isEmpty()) {

            for(int i = 0; i < meetingLocations.size(); i++) {

                Location meetingLocation = meetingLocations.get(i);
                String serializedMeetingLocation = plugin.serializeLocation(meetingLocation);

                plugin.getArenaConfig().getConfig().set(id.toString() + ".Meeting." + i, serializedMeetingLocation);

            }

        }

        plugin.getArenaConfig().save();

    }

}
