package com.oveln.ovvote;

import com.oveln.ovvote.Listener.PlayerChat;
import com.oveln.ovvote.command.onvote;
import com.oveln.ovvote.votebar.Votebar;
import com.oveln.utils.Chater;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class main extends JavaPlugin {
    private final Logger logger = getLogger();
    private final PluginDescriptionFile descriptionFile = getDescription();
    private final FileConfiguration config = getConfig();
    private static main Instance;
    private Votebar votebar;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        votebar = new Votebar();
        Instance  = this;

        getServer().getPluginManager().registerEvents(new PlayerChat() , this);
        getCommand("vote").setExecutor(new onvote());

        String s =descriptionFile.getName()+descriptionFile.getVersion()+"&2启动成功  &c作者"+descriptionFile.getAuthors().get(0);
        logger.info(Chater.t(s));
    }
    @Override
    public void onDisable() {
        String s =descriptionFile.getName()+descriptionFile.getVersion()+"&2关闭成功  &c作者"+descriptionFile.getAuthors().get(0);
        logger.info(Chater.t(s));
    }

    public static main getInstance() {
        return Instance;
    }
    public Votebar getVotebar() {return votebar;}
}
