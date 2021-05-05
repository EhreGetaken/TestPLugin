package eu.imposdev.testplugin.listener;

import eu.imposdev.testplugin.TestPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        TestPlugin.getInstance().getScoreboardAPI().sendScoreboard(player);
    }

}
