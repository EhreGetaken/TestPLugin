package eu.imposdev.testplugin.listener;

import eu.imposdev.testplugin.TestPlugin;
import net.core.api.ItemBuilder;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player && event.getWhoClicked() != null) {
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem() == null) return;
            if (event.getClickedInventory() == null) return;
            if (event.getAction() == null) return;
            if (event.getInventory() == null) return;

            ItemStack time = new ItemBuilder(Material.WATCH).setName("§7Ändere die §a§lZeit").setAmount(0).build();
            ItemStack weather = new ItemBuilder(Material.WATER_BUCKET).setName("§7Ändere das §a§lWetter").setAmount(1).build();
            ItemStack gameMode = new ItemBuilder(Material.REDSTONE).setName("§7Ändere deinen §a§lSpielmodus").setAmount(2).build();

            if (event.getClickedInventory().getTitle().equalsIgnoreCase("§6§lController")) {
                event.setCancelled(true);
                if (event.getCurrentItem().equals(time)) {
                    player.closeInventory();
                    player.updateInventory();
                    Bukkit.getScheduler().scheduleSyncDelayedTask(TestPlugin.getInstance(), () -> {
                        new AnvilGUI(TestPlugin.getInstance(), player, "zahl", (player2, reply) -> {
                            System.out.println(reply);
                            try {
                                int time2 = Integer.parseInt(reply);
                                player.getWorld().setTime((long) time2);
                                player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.timeSet")
                                        .replaceAll("&", "§").replace("%time%", reply)
                                        .replace("%world%", player.getWorld().getName()));
                                return null;
                            } catch (NumberFormatException exception) {
                                player.sendMessage(TestPlugin.getInstance().getConfig()
                                        .getString("Messages.timeNotInt").replaceAll("&", "§"));
                                return "Das ist keine Zahl!";
                            }
                        });
                    }, 5L);
                }
                if (event.getCurrentItem().equals(weather)) {
                    Inventory inventory = Bukkit.createInventory(null, 9, "§7Änder das §a§lWetter");

                    ItemStack clear = new ItemBuilder(new ItemStack(Material.STAINED_CLAY, 1, (byte) 5)).setName("§aSonne").build();
                    ItemStack storm = new ItemBuilder(new ItemStack(Material.STAINED_CLAY, 1, (byte) 11)).setName("§aSturm").build();
                    ItemStack thunder = new ItemBuilder(new ItemStack(Material.STAINED_CLAY, 1, (byte) 14)).setName("§aSturm und Donner").build();

                    inventory.setItem(0, clear);
                    inventory.setItem(4, storm);
                    inventory.setItem(8, thunder);

                    player.openInventory(inventory);
                }
                if (event.getCurrentItem().equals(gameMode)) {
                    Inventory inventory = Bukkit.createInventory(null, 9, "§7Änder deinen §a§lSpielmodus");

                    ItemStack creative = new ItemBuilder(new ItemStack(Material.STAINED_CLAY, 1, (byte) 5)).setName("§aKreativ").build();
                    ItemStack adventure = new ItemBuilder(new ItemStack(Material.STAINED_CLAY, 1, (byte) 4)).setName("§aAbenteuer").build();
                    ItemStack survival = new ItemBuilder(new ItemStack(Material.STAINED_CLAY, 1, (byte) 14)).setName("§aÜberleben").build();
                    ItemStack spectator = new ItemBuilder(new ItemStack(Material.STAINED_CLAY, 1, (byte) 15)).setName("§aZuschauer").build();

                    inventory.setItem(1, creative);
                    inventory.setItem(3, adventure);
                    inventory.setItem(5, survival);
                    inventory.setItem(7, spectator);

                    player.openInventory(inventory);
                }
            }
            if (event.getClickedInventory().getTitle().equalsIgnoreCase("§7Änder das §a§lWetter")) {
                ItemStack clear = new ItemBuilder(new ItemStack(Material.STAINED_CLAY, 1, (byte) 5)).setName("§aSonne").build();
                ItemStack storm = new ItemBuilder(new ItemStack(Material.STAINED_CLAY, 1, (byte) 11)).setName("§aSturm").build();
                ItemStack thunder = new ItemBuilder(new ItemStack(Material.STAINED_CLAY, 1, (byte) 14)).setName("§aSturm und Donner").build();
                if (event.getCurrentItem().equals(clear)) {
                    player.closeInventory();
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.weatherSet")
                            .replaceAll("&", "§").replace("%world%", player.getWorld().getName())
                            .replace("%type%", "clear"));
                    player.getWorld().setThundering(false);
                    player.getWorld().setStorm(false);
                }
                if (event.getCurrentItem().equals(storm)) {
                    player.closeInventory();
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.weatherSet")
                            .replaceAll("&", "§").replace("%world%", player.getWorld().getName())
                            .replace("%type%", "storm"));
                    player.getWorld().setThundering(false);
                    player.getWorld().setStorm(true);
                }
                if (event.getCurrentItem().equals(thunder)) {
                    player.closeInventory();
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.weatherSet")
                            .replaceAll("&", "§").replace("%world%", player.getWorld().getName())
                            .replace("%type%", "thunder"));
                    player.getWorld().setThundering(true);
                    player.getWorld().setStorm(true);
                }
            }
            if (event.getClickedInventory().getTitle().equalsIgnoreCase("§7Änder deinen §a§lSpielmodus")) {
                ItemStack creative = new ItemBuilder(new ItemStack(Material.STAINED_CLAY, 1, (byte) 5)).setName("§aKreativ").build();
                ItemStack adventure = new ItemBuilder(new ItemStack(Material.STAINED_CLAY, 1, (byte) 4)).setName("§aAbenteuer").build();
                ItemStack survival = new ItemBuilder(new ItemStack(Material.STAINED_CLAY, 1, (byte) 14)).setName("§aÜberleben").build();
                ItemStack spectator = new ItemBuilder(new ItemStack(Material.STAINED_CLAY, 1, (byte) 15)).setName("§aZuschauer").build();
                if (event.getCurrentItem().equals(creative)) {
                    player.setGameMode(GameMode.CREATIVE);
                    player.closeInventory();
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.GameModeSet")
                            .replaceAll("&", "§").replace("%player%", player.getName())
                            .replace("%mode%", GameMode.CREATIVE.name()));
                }
                if (event.getCurrentItem().equals(adventure)) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.closeInventory();
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.GameModeSet")
                            .replaceAll("&", "§").replace("%player%", player.getName())
                            .replace("%mode%", GameMode.ADVENTURE.name()));
                }
                if (event.getCurrentItem().equals(survival)) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.closeInventory();
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.GameModeSet")
                            .replaceAll("&", "§").replace("%player%", player.getName())
                            .replace("%mode%", GameMode.SURVIVAL.name()));
                }
                if (event.getCurrentItem().equals(spectator)) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.closeInventory();
                    player.sendMessage(TestPlugin.getInstance().getConfig().getString("Messages.GameModeSet")
                            .replaceAll("&", "§").replace("%player%", player.getName())
                            .replace("%mode%", GameMode.SPECTATOR.name()));
                }
            }
        }
    }

}
