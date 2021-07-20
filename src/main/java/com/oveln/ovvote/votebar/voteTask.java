package com.oveln.ovvote.votebar;

import com.oveln.ovvote.main;
import org.bukkit.scheduler.BukkitRunnable;

public class voteTask extends BukkitRunnable {
    private final Integer time;

    public voteTask(Integer time) {
        this.time = time;
    }
    @Override
    public void run() {
        if (time == 1) {
            main.getInstance().getVotebar().over();
        }else {
            main.getInstance().getVotebar().update(time.doubleValue() / main.getInstance().getConfig().getInt("TimeLimit"));
            new voteTask(time-1).runTaskLater(main.getInstance() , 20);
        }
    }
}
