package com.oveln.utils;

import com.oveln.ovvote.main;
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
    public static String getTitle(Integer yes ,Integer no , String cmdname , String target) {
        String s = main.getInstance().getConfig().getString("Title");
        s = s.replace("%cmd%" , cmdname).replace("%player%" , target).replace("%yes%" , yes.toString()).replace("%no%",no.toString());
        return t(s);
    }
}
