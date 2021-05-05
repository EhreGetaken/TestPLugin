package eu.imposdev.testplugin.commands;

import eu.imposdev.testplugin.TestPlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameMode_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.GameModeHelp")
                        .replaceAll("&", "§"));
            }
            if (args.length == 1) {
                String mode = args[0].toUpperCase();
                try {
                    GameMode gameMode = GameMode.valueOf(mode);
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.GameModeSet")
                            .replaceAll("&", "§").replace("%player%", player.getName())
                            .replace("%mode%", gameMode.name()));
                    player.setGameMode(gameMode);
                } catch (IllegalArgumentException exception) {
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.noGameMode").
                            replaceAll("&", "§"));
                }
            }
            if (args.length == 2) {
                String mode = args[0].toUpperCase();
                try {
                    GameMode gameMode = GameMode.valueOf(mode);
                    String playerName = args[1];
                    Player target = null;
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (all.getName().equalsIgnoreCase(playerName)) {
                            target = all;
                        }
                    }
                    if (target != null) {
                        player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.GameModeSet")
                                .replaceAll("&", "§").replace("%player%", target.getName())
                                .replace("%mode%", gameMode.name()));
                        target.setGameMode(gameMode);
                    } else {
                        player.sendMessage(TestPlugin.getInstance().getConfig().getString("noPlayer")
                                .replaceAll("&", "§"));
                    }
                } catch (IllegalArgumentException exception) {
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.noGameMode").
                            replaceAll("&", "§"));
                }
            }
        }
        return true;
    }
}
