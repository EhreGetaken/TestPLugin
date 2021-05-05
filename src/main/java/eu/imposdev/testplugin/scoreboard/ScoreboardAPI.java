package eu.imposdev.testplugin.scoreboard;

import eu.imposdev.testplugin.TestPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScoreboardAPI {

    private int scheduler;

    public void sendScoreboard(Player player) {
        DateTimeFormatter date = DateTimeFormatter.ofPattern(TestPlugin.getInstance().getConfig().getString("Format.date"));
        DateTimeFormatter time = DateTimeFormatter.ofPattern(TestPlugin.getInstance().getConfig().getString("Format.time"));
        LocalDateTime now = LocalDateTime.now();
        FastBoard fastBoard = new FastBoard(player);
        fastBoard.updateTitle(TestPlugin.getInstance().getConfig().getString("Scoreboard.title")
                .replaceAll("&", "§"));
        for (int i = 0; i < TestPlugin.getInstance().getLines().size(); i++) {
            String line = TestPlugin.getInstance().getLines().get(i).replaceAll("&", "§")
                    .replaceAll("%date%", date.format(now)).replaceAll("%time%", time.format(now))
                    .replaceAll("%players%", String.valueOf(Bukkit.getOnlinePlayers().size()));
            fastBoard.updateLine(i, line);
        }
    }

    public void updateBoard() {
        scheduler = Bukkit.getScheduler().scheduleAsyncRepeatingTask(TestPlugin.getInstance(), () -> {
            Bukkit.getOnlinePlayers().forEach(this::sendScoreboard);
        }, 20L * (long) TestPlugin.getInstance().getConfig().getInt("Scoreboard.updateTimeInSeconds"),
                20L * (long) TestPlugin.getInstance().getConfig().getInt("Scoreboard.updateTimeInSeconds"));
    }

    public void shutdownScoreboard() {
        Bukkit.getScheduler().cancelTask(scheduler);
    }

}
