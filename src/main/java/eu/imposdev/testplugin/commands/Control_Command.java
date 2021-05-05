package eu.imposdev.testplugin.commands;

import net.core.api.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Control_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory inventory = Bukkit.createInventory(null, 9, "§6§lController");

            ItemStack time = new ItemBuilder(Material.WATCH).setName("§7Ändere die §a§lZeit").setAmount(0).build();
            ItemStack weather = new ItemBuilder(Material.WATER_BUCKET).setName("§7Ändere das §a§lWetter").setAmount(1).build();
            ItemStack gameMode = new ItemBuilder(Material.REDSTONE).setName("§7Ändere deinen §a§lSpielmodus").setAmount(2).build();

            inventory.setItem(0, time);
            inventory.setItem(4, weather);
            inventory.setItem(8, gameMode);
            player.openInventory(inventory);

        }
        return true;
    }
}
