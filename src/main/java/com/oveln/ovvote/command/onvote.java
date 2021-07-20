package com.oveln.ovvote.command;

import com.oveln.ovvote.main;
import com.oveln.utils.Chater;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class onvote implements CommandExecutor , TabCompleter {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Chater.t("&c只有玩家可以执行该指令"));
            return true;
        }
        if (!main.getInstance().getVotebar().isAble()) {
            commandSender.sendMessage(Chater.t("&c有一个投票正在进行中"));
            return true;
        }
        if (args.length == 2) {
            Player player = (Player) commandSender;
            if (!main.getInstance().getVotebar().canStart(player)) {
                player.sendMessage(Chater.t("&c你现在还不能发起投票"));
                return true;
            }
            ConfigurationSection commands = main.getInstance().getConfig().getConfigurationSection("Commands");
            if (commands.getKeys(false).contains(args[0])) {
                if (main.getInstance().getServer().getPlayer(args[1]) != null) {
                    String cmd = "&6"+player.getName()+"&f发起了对&6"+args[1]+"&f执行&2"+args[0]+"&f的投票";
                    Bukkit.broadcastMessage(Chater.t(cmd));
                    cmd = "同意请输入[&2y&f]   反对请输入[&cn&f]";
                    Bukkit.broadcastMessage(Chater.t(cmd));
                    cmd = commands.getConfigurationSection(args[0]).getString("command").replace("%player%" , args[1]);
                    main.getInstance().getVotebar().start(player , cmd , args[1] , args[0]);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> ret = new ArrayList<>();
        if (args.length==1)
            ret = new ArrayList<>(main.getInstance().getConfig().getConfigurationSection("Commands").getKeys(false));
        if (args.length==2) {
            for (Player p : main.getInstance().getServer().getOnlinePlayers())
                ret.add(p.getName());
        }
        return ret;
    }
}
