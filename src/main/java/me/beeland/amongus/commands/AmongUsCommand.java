package me.beeland.amongus.commands;

import me.beeland.amongus.AmongUs;
import me.beeland.amongus.Language;
import me.beeland.amongus.game.lobby.Lobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class AmongUsCommand implements CommandExecutor, TabExecutor {

    private AmongUs plugin;

    public AmongUsCommand(AmongUs plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            plugin.send(sender, Language.PLAYER_ONLY);
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 1) {

            Lobby lobby = new Lobby(plugin, player);

            plugin.getLobbyManager().addLobby(lobby);
            player.sendMessage("JOIN CODE -> " + lobby.getJoinCode());

        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return null;
    }
}
