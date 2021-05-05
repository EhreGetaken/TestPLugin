package eu.imposdev.testplugin;

import eu.imposdev.testplugin.api.TabCompleter;
import eu.imposdev.testplugin.commands.ChangeTime_Command;
import eu.imposdev.testplugin.commands.ChangeWeather_Command;
import eu.imposdev.testplugin.commands.Control_Command;
import eu.imposdev.testplugin.commands.GameMode_Command;
import eu.imposdev.testplugin.listener.AsyncPlayerChatListener;
import eu.imposdev.testplugin.listener.InventoryClickListener;
import eu.imposdev.testplugin.listener.PlayerJoinListener;
import eu.imposdev.testplugin.scoreboard.ScoreboardAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class TestPlugin extends JavaPlugin {

    private static TestPlugin instance;

    private ScoreboardAPI scoreboardAPI;

    private ArrayList<String> lines = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;

        scoreboardAPI = new ScoreboardAPI();

        ArrayList<String> linez = new ArrayList<>();
        linez.add(" ");
        linez.add("Date");
        linez.add("%date%");
        linez.add("  ");
        linez.add("Time");
        linez.add("%time%");
        linez.add("   ");
        linez.add("Players");
        linez.add("%players%");

        getConfig().options().copyDefaults(true);
        getConfig().options().header("TestPlugin by Espen da Silva. Copyright (c) 2021. All rights reserved. Project provided by Reduxo.net");
        getConfig().addDefault("Chat.format", "&a%name% &8> &7%message%");
        getConfig().addDefault("Scoreboard.title", "&f&lSERVER");
        getConfig().addDefault("Scoreboard.lines", linez);
        getConfig().addDefault("Scoreboard.updateTimeInSeconds", 10);
        getConfig().addDefault("Format.date", "yyyy/MM/dd");
        getConfig().addDefault("Format.time", "HH:mm:ss");
        getConfig().addDefault("Messages.timeHelp", "&7Nutze&8: &7/changetime [world] (optional) [time]");
        getConfig().addDefault("Messages.timeSet", "&7Du hast die Zeit in der Welt &a%world% &7auf &a%time% &7gesetzt.");
        getConfig().addDefault("Messages.timeNotInt", "&7Der eingegebene Wert ist keine Zahl!");
        getConfig().addDefault("Messages.timeNoWorld", "&7Diese Welt existiert nicht!");
        getConfig().addDefault("Messages.weatherHelp", "&7Nutze&8: &7/changeweather [world] (optional) clear/storm/thunder");
        getConfig().addDefault("Messages.weatherSet", "&7Du hast das Wetter in der Welt &a%world% zu &a%type% &7geändert.");
        getConfig().addDefault("Messages.weatherNoWorld", "&7Diese Welt exisitiert nicht!");
        getConfig().addDefault("Messages.weatherNoWeather", "&7Diese Art von Wetter existiert nicht!");
        getConfig().addDefault("Messages.GameModeHelp", "&7Nutze&8: &7/gm [mode] [player] (optional)");
        getConfig().addDefault("Messages.GameModeSet", "&7Du hast den Spielmodus von &a%player% &7auf &a%mode% &7gesetzt.");
        getConfig().addDefault("Messages.noGameMode", "&7Dieser Spielmodus exisitiert nicht!");
        getConfig().addDefault("Messages.noPlayer", "&7Dieser Spieler ist nicht online!");
        saveConfig();

        lines = (ArrayList<String>) getConfig().getStringList("Scoreboard.lines");

        registerListener();
        registerCommands();

        scoreboardAPI.updateBoard();

    }

    @Override
    public void onDisable() {
        scoreboardAPI.shutdownScoreboard();
    }

    private void registerListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new AsyncPlayerChatListener(), instance);
        pluginManager.registerEvents(new PlayerJoinListener(), instance);
        pluginManager.registerEvents(new InventoryClickListener(), instance);
    }

    private void registerCommands() {
        getCommand("changetime").setExecutor(new ChangeTime_Command());
        getCommand("changeweather").setExecutor(new ChangeWeather_Command());
        getCommand("gm").setExecutor(new GameMode_Command());
        getCommand("control").setExecutor(new Control_Command());

        getCommand("gm").setTabCompleter(new TabCompleter());
    }

    public static TestPlugin getInstance() {
        return instance;
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    public ScoreboardAPI getScoreboardAPI() {
        return scoreboardAPI;
    }
}
