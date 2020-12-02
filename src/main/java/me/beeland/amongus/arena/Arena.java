package me.beeland.amongus.arena;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.beeland.amongus.AmongUs;
import me.beeland.amongus.game.lobby.Lobby;
import me.beeland.amongus.game.task.TaskType;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Arena {

    private AmongUs plugin;

    private UUID id;
    private String shortId;

    private Location lobbyLocation;
    private Location settingsLocation;
    private List<Location> meetingLocations;
    private HashMap<TaskType, List<Location>> taskLocations;

    private Lobby owningLobby;

    public Arena(AmongUs plugin, UUID id) {
        this.plugin = plugin;
        this.id = id;
        this.shortId = id.toString().split("-")[0];
        this.meetingLocations = Lists.newArrayList();
        this.taskLocations = Maps.newHashMap();

        for(TaskType taskType : TaskType.values()) {
            taskLocations.put(taskType, Lists.newArrayList());
        }

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

    public Location getSettingsLocation() {
        return settingsLocation;
    }

    public void setSettingsLocation(Location settingsLocation) {
        this.settingsLocation = settingsLocation;
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

    public HashMap<TaskType, List<Location>> getTaskLocations() {
        return taskLocations;
    }

    public void addTaskLocation(TaskType type, Location location) {
        taskLocations.get(type).add(location);
    }

    public void removeTaskLocation(TaskType type, Location location) {
        taskLocations.get(type).remove(location);
    }

    public Lobby getOwningLobby() {
        return owningLobby;
    }

    public void setOwningLobby(Lobby owningLobby) {
        this.owningLobby = owningLobby;
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

        for(Map.Entry<TaskType, List<Location>> entry : taskLocations.entrySet()) {

            if(!entry.getValue().isEmpty()) {

                for(int i = 0; i < entry.getValue().size(); i++) {

                    Location taskLocation = entry.getValue().get(i);
                    String serializedTaskLocation = plugin.serializeLocation(taskLocation);

                    plugin.getArenaConfig().getConfig().set(id.toString() + ".Tasks." + entry.getKey().toString() + "." + i, serializedTaskLocation);

                }

            }

        }

        plugin.getArenaConfig().save();

    }

}
