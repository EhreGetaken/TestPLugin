package eu.imposdev.testplugin.commands;

import eu.imposdev.testplugin.TestPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangeTime_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.timeHelp")
                        .replaceAll("&", "§"));
            }
            if (args.length == 1) {
                try {
                    int time = Integer.parseInt(args[0]);
                    player.getWorld().setTime((long) time);
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.timeSet")
                            .replaceAll("&", "§").replace("%time%", args[0])
                            .replace("%world%", player.getWorld().getName()));
                } catch (NumberFormatException exception) {
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.timeNotInt")
                            .replaceAll("&", "§"));
                }
            }
            if (args.length == 2) {
                String world = args[0];
                try {
                    int time = Integer.parseInt(args[1]);
                    World bukkitWorld = Bukkit.getWorld(world);
                    if (bukkitWorld != null) {
                        bukkitWorld.setTime(time);
                        player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.timeSet")
                                .replaceAll("&", "§").replace("%time%", args[0])
                                .replace("%world%", world));
                    } else {
                        player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.timeNoWorld")
                                .replaceAll("&", "§"));
                    }
                } catch (NumberFormatException exception) {
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.timeNotInt")
                            .replaceAll("&", "§"));
                }
            }
        }
        return true;
    }
}
