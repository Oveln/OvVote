package com.oveln.ovvote.votebar;

import com.oveln.ovvote.main;
import com.oveln.utils.Chater;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Votebar {
    private final BossBar bossbar;
    private Integer yes , no;
    private Set<Player> Voted;
    private Map<Player , Long> StartTime;
    String cmd;

    public Votebar() {
        yes = 0;
        no = 0;
        bossbar  = Bukkit.createBossBar("投票" , BarColor.RED , BarStyle.SOLID);
        Voted = new HashSet<>();
        StartTime = new HashMap<>();
        cmd = "";
    }
    private void reset() {
        bossbar.removeAll();
        yes = 0;
        no = 0;
        Voted.clear();
    }
    private String getTitle() {
        return Chater.t("&2赞同&6"+yes+"&f人----&c反对&6"+no+"&f人");
    }
    public boolean AddYes(Player player) {
        if (!Voted.contains(player)) {
            yes++;
            bossbar.setTitle(getTitle());
            Voted.add(player);
            return true;
        }else {
            player.sendMessage(Chater.t("&c你已经投过票了"));
            return false;
        }
    }
    public boolean AddNo(Player player) {
        if (!Voted.contains(player)) {
            no++;
            bossbar.setTitle(getTitle());
            Voted.add(player);
            return true;
        }else {
            player.sendMessage(Chater.t("&c你已经投过票了"));
            return false;
        }
    }
    public void start(Player player ,String cmd) {
        this.cmd = cmd;
        bossbar.setTitle(getTitle());
        StartTime.put(player , System.currentTimeMillis());
        for (Player p : Bukkit.getOnlinePlayers())
            bossbar.addPlayer(p);
        bossbar.setProgress(1);
        new voteTask(main.getInstance().getConfig().getInt("TimeLimit")-1).runTaskLater(main.getInstance(),20);
    }
    public void over() {
        if (yes*main.getInstance().getConfig().getDouble("coefficient")>no) {
            main.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
            System.out.print(cmd);
            Bukkit.broadcastMessage(Chater.t("&2投票结果成功"));
        }else {
            Bukkit.broadcastMessage(Chater.t("&c投票结果失败"));
        }
        reset();
    }
    public void update(Double x) {
        bossbar.setProgress(x);
    }
    public boolean canStart(Player player) {
        if (!StartTime.containsKey(player) || (System.currentTimeMillis() - StartTime.get(player)) > main.getInstance().getConfig().getInt("interval")* 1000L)
            return true;
        return false;
    }
}
