package com.oveln.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chater {
    public static String t(String text) {
        return ChatColor.translateAlternateColorCodes('&',text);
    }

    public static void Sendstrings(Player player ,String[] strings) {
        for (String i :strings) {
            player.sendMessage(i);
        }
    }
}
