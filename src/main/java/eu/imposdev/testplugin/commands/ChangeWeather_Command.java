package eu.imposdev.testplugin.commands;

import eu.imposdev.testplugin.TestPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangeWeather_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.weatherHelp")
                        .replaceAll("&", "§"));
            }
            if (args.length == 1) {
                String type = args[0];
                if (type.equalsIgnoreCase("clear")) {
                    player.getWorld().setThundering(false);
                    player.getWorld().setStorm(false);
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.weatherSet")
                            .replaceAll("&", "§").replace("%world%", player.getWorld().getName())
                            .replace("%type%", args[0]));
                } else if (type.equalsIgnoreCase("storm")) {
                    player.getWorld().setThundering(false);
                    player.getWorld().setStorm(true);
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.weatherSet")
                            .replaceAll("&", "§").replace("%world%", player.getWorld().getName())
                            .replace("%type%", args[0]));
                } else if (type.equalsIgnoreCase("thunder")) {
                    player.getWorld().setThundering(true);
                    player.getWorld().setStorm(false);
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.weatherSet")
                            .replaceAll("&", "§").replace("%world%", player.getWorld().getName())
                            .replace("%type%", args[0]));
                } else {
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.weatherNoWeather")
                            .replaceAll("&", "§"));
                }
            }
            if (args.length == 2) {
                String world = args[0];
                String type = args[1];
                World bukkitWorld = Bukkit.getWorld(world);
                if (bukkitWorld != null) {
                    if (type.equalsIgnoreCase("clear")) {
                        bukkitWorld.setThundering(false);
                        bukkitWorld.setStorm(false);
                        player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.weatherSet")
                                .replaceAll("&", "§").replace("%world%", "bukkitWorld")
                                .replace("%type%", args[0]));
                    } else if (type.equalsIgnoreCase("storm")) {
                        bukkitWorld.setThundering(false);
                        bukkitWorld.setStorm(true);
                        player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.weatherSet")
                                .replaceAll("&", "§").replace("%world%", "bukkitWorld")
                                .replace("%type%", args[0]));
                    } else if (type.equalsIgnoreCase("thunder")) {
                        bukkitWorld.setThundering(true);
                        bukkitWorld.setStorm(true);
                        player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.weatherSet")
                                .replaceAll("&", "§").replace("%world%", "bukkitWorld")
                                .replace("%type%", args[0]));
                    } else {
                        player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.weatherNoWeather")
                                .replaceAll("&", "§"));
                    }
                } else {
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.weatherNoWorld")
                            .replaceAll("&", "§"));
                }
            }
        }
        return true;
    }
}
