package me.beeland.amongus.arena;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

public class ArenaManager {

    private List<Arena> arenas;

    public ArenaManager() {
        this.arenas = Lists.newArrayList();
    }

    public List<Arena> getArenas() {
        return arenas;
    }

    public void addArena(Arena arena) {
        this.arenas.add(arena);
    }

    public void removeArena(Arena arena) {
        this.arenas.remove(arena);
    }

    public Arena getArenaById(String id) {

        for(Arena arena : arenas) {
            if(arena.getId().equalsIgnoreCase(id)) return arena;
        }

        return null;
    }

}
