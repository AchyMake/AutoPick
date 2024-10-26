package org.achymake.autopick.handlers;

import org.achymake.autopick.AutoPick;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class ScheduleHandler {
    private AutoPick getInstance() {
        return AutoPick.getInstance();
    }
    public BukkitScheduler getScheduler() {
        return getInstance().getBukkitScheduler();
    }
    public BukkitTask runLater(Runnable runnable, long timer) {
        return getScheduler().runTaskLater(getInstance(), runnable, timer);
    }
    public void runAsynchronously(Runnable runnable) {
        getScheduler().runTaskAsynchronously(getInstance(), runnable);
    }
    public boolean isQueued(int taskID) {
        return getScheduler().isQueued(taskID);
    }
    public void cancel(int taskID) {
        if (isQueued(taskID)) {
            getScheduler().cancelTask(taskID);
        }
    }
    public void cancelAll() {
        getScheduler().cancelTasks(getInstance());
    }
}