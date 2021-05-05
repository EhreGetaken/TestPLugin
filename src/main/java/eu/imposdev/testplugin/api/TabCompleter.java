package eu.imposdev.testplugin.api;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("gm")) {
            if (args.length == 1) {
                List<String> arguments = new ArrayList<>();
                arguments.add("CREATIVE");
                arguments.add("ADVENTURE");
                arguments.add("SURVIVAL");
                arguments.add("SPECTATOR");

                return arguments;
            }
            if (args.length == 2) {
                List<String> arguments = new ArrayList<>();
                Bukkit.getOnlinePlayers().forEach(all -> {
                    arguments.add(all.getName());
                });
                return arguments;
            }
        }
        return null;
    }
}
