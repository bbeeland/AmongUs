package me.beeland.amongus.commands;

import me.beeland.amongus.AmongUs;
import me.beeland.amongus.Language;
import me.beeland.amongus.arena.Arena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class AmongUsAdminCommand implements CommandExecutor, TabExecutor {

    private AmongUs plugin;

    public AmongUsAdminCommand(AmongUs plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            plugin.send(sender, Language.PLAYER_ONLY);
            return true;
        }

        if(!sender.hasPermission("amongus.admin")) {
            //TODO: Get message from language file and send error
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 2) {

            if(args[0].equalsIgnoreCase("arena")) {

                if(args[1].equalsIgnoreCase("create")) {

                    Arena arena = new Arena(plugin, UUID.randomUUID());
                    plugin.getArenaManager().addArena(arena);

                    player.sendMessage("arena id: " + arena.getShortId());
                    return true;

                }

            }

        }

        if(args.length == 4) {

            if(args[0].equalsIgnoreCase("arena")) {

                if(args[1].equalsIgnoreCase("lobby")) {

                    if(args[2].equalsIgnoreCase("set")) {

                        String shortId = args[3];
                        Arena arena = plugin.getArenaManager().getArenaByShortId(shortId);

                        if(arena == null) {

                            sender.sendMessage("Arena not found!");
                            return true;

                        }

                        arena.setLobbyLocation(player.getLocation());
                        player.sendMessage("Location set!");
                        return true;


                    }

                }

                if(args[1].equalsIgnoreCase("meeting")) {

                    if(args[2].equalsIgnoreCase("add")) {

                        String shortId = args[3];
                        Arena arena = plugin.getArenaManager().getArenaByShortId(shortId);

                        if(arena == null) {

                            sender.sendMessage("Arena not found!");
                            return true;

                        }

                        arena.addMeetingLocation(player.getLocation());
                        player.sendMessage("Location added!");
                        return true;

                    }

                }

            }

        }

        if(args.length == 5) {

            if(args[0].equalsIgnoreCase("arena")) {

                if(args[1].equalsIgnoreCase("meeting")) {

                    if(args[2].equalsIgnoreCase("add")) {

                        String shortId = args[3];
                        Arena arena = plugin.getArenaManager().getArenaByShortId(shortId);

                        if(arena == null) {

                            sender.sendMessage("Arena not found!");
                            return true;

                        }

                        arena.setLobbyLocation(player.getLocation());
                        player.sendMessage("Location set!");
                        return true;


                    }

                }

            }

        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
