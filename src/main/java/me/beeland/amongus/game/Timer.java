package me.beeland.amongus.game;

import me.beeland.amongus.AmongUs;
import me.beeland.amongus.Language;
import me.beeland.amongus.game.lobby.Lobby;
import me.beeland.amongus.game.session.PlayerSession;
import me.beeland.amongus.game.task.UploadDataTask;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public class Timer extends BukkitRunnable {

    private AmongUs plugin;
    private Lobby lobby;

    private int[] progressBarSlots = { 11, 12, 13, 14, 15 };

    public Timer(AmongUs plugin, Lobby lobby) {

        this.plugin = plugin;
        this.lobby = lobby;

        this.runTaskTimerAsynchronously(plugin, 1, 20);
    }

    @Override
    public void run() {

        if(lobby.getState() == GameState.LOBBY) return;

        if(lobby.getState() == GameState.IN_GAME) {

            updateUploadDataTask();
        }

    }

    private void updateUploadDataTask() {

        for(Map.Entry<PlayerSession, UploadDataTask> entry : lobby.getDataUploadQueue().entrySet()) {

            PlayerSession session = entry.getKey();

            if(session.getTaskStage() == 5) {

                session.completeTask(entry.getValue());

            } else {

                session.nextTaskStage();

            }

            Inventory inventory = Bukkit.createInventory(null, 27, plugin.getLang(Language.UPLOAD_DATA_MENU_TITLE));

            ItemStack fillItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta fillItemMeta = fillItem.getItemMeta();
            fillItemMeta.setDisplayName(" ");
            fillItem.setItemMeta(fillItemMeta);

            ItemStack redGlassItem = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemMeta redGlassItemMeta = redGlassItem.getItemMeta();
            redGlassItemMeta.setDisplayName(" ");
            redGlassItem.setItemMeta(redGlassItemMeta);

            ItemStack greenGlassItem = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta greenGlassMeta = greenGlassItem.getItemMeta();
            greenGlassMeta.setDisplayName(" ");
            greenGlassItem.setItemMeta(greenGlassMeta);

            for(int i = 0; i < 27; i++) {
                inventory.setItem(i, fillItem);
            }

            for(int i : progressBarSlots) {
                inventory.setItem(i, redGlassItem);
            }

            for(int i = 0; i < session.getTaskStage(); i++) {

                inventory.setItem(i, greenGlassItem);

            }

        }

    }

}
