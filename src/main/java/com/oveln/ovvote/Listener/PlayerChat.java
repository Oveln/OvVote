package com.oveln.ovvote.Listener;

import com.oveln.ovvote.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {
    @EventHandler
    public boolean onPlayerChat(AsyncPlayerChatEvent event) {
        if (event.getMessage().equals("n")) {
            if (!main.getInstance().getVotebar().AddNo(event.getPlayer()))
                event.setCancelled(true);
        }
        if (event.getMessage().equals("y")) {
            if (!main.getInstance().getVotebar().AddYes(event.getPlayer()))
                event.setCancelled(true);
        }
        return true;
    }
}
