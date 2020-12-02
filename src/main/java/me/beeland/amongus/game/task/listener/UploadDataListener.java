package me.beeland.amongus.game.task.listener;

import me.beeland.amongus.AmongUs;
import me.beeland.amongus.Language;
import me.beeland.amongus.game.GameState;
import me.beeland.amongus.game.lobby.Lobby;
import me.beeland.amongus.game.session.PlayerSession;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class UploadDataListener implements Listener {

    private AmongUs plugin;

    public UploadDataListener(AmongUs plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onInventoryClose(InventoryCloseEvent e) {

        Lobby lobby = plugin.getLobbyManager().getLobbyByPlayer((Player) e.getPlayer());
        PlayerSession session = lobby.getSessionByUUID(e.getPlayer().getUniqueId());

        if(lobby == null || lobby.getState() != GameState.IN_GAME) return;
        if(!lobby.getDataUploadQueue().containsKey(session)) return;

        plugin.send(e.getPlayer(), Language.TASK_RESTARTED);
        session.setTaskStage(0);
    }

    @EventHandler
    void onInventoryClick(InventoryClickEvent e) {

        if(e.getClickedInventory() == null) return;
        if(e.getCurrentItem() == null) return;
        if(e.getView().getTitle().equalsIgnoreCase(plugin.getLang(Language.UPLOAD_DATA_MENU_TITLE))) return;

        Lobby lobby = plugin.getLobbyManager().getLobbyByPlayer((Player) e.getWhoClicked());

        if(lobby == null) return;

        e.setCancelled(true);
    }

}
