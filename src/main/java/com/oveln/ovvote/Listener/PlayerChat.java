package com.oveln.ovvote.Listener;

import com.oveln.ovvote.main;
import com.oveln.utils.Chater;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {
    @EventHandler
    public boolean onPlayerChat(AsyncPlayerChatEvent event) {
        if (main.getInstance().getVotebar().isAble()) return true;
        if (event.getMessage().equals("n")) {
            if (!main.getInstance().getVotebar().AddNo(event.getPlayer()))
//                event.setMessage(Chater.t("&c反对"));
                event.setCancelled(true);
        }
        if (event.getMessage().equals("y")) {
            if (!main.getInstance().getVotebar().AddYes(event.getPlayer()))
//                event.setMessage(Chater.t("&2同意"));
                event.setCancelled(true);
        }
        return true;
    }
}
