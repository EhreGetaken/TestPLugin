package eu.imposdev.testplugin.listener;

import eu.imposdev.testplugin.TestPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        //fix for spigot error in chat formatting %
        message = message.replaceAll("%%", "%");

        String format = TestPlugin.getInstance().getConfig().getString("Chat.format")
        .replaceAll("&", "§").replace("%name%", player.getName()).replace("%message%", message);
        event.setFormat(format);
    }

}
