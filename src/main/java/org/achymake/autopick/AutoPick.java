package org.achymake.autopick;

import org.achymake.autopick.data.Message;
import org.achymake.autopick.handlers.MaterialHandler;
import org.achymake.autopick.handlers.ScheduleHandler;
import org.achymake.autopick.listeners.BlockDropItem;
import org.achymake.autopick.listeners.EntityDeath;
import org.achymake.autopick.listeners.PlayerJoin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class AutoPick extends JavaPlugin {
    private static AutoPick instance;
    private Message message;
    private MaterialHandler materialHandler;
    private ScheduleHandler scheduleHandler;
    private PluginManager manager;
    private BukkitScheduler bukkitScheduler;
    private UpdateChecker updateChecker;
    @Override
    public void onEnable() {
        instance = this;
        manager = getServer().getPluginManager();
        bukkitScheduler = getServer().getScheduler();
        message = new Message();
        materialHandler = new MaterialHandler();
        scheduleHandler = new ScheduleHandler();
        updateChecker = new UpdateChecker();
        events();
        sendInfo("Enabled for " + getMinecraftProvider() + " " + getMinecraftVersion());
    }
    @Override
    public void onDisable() {
        sendInfo("Disabled for " + getMinecraftProvider() + " " + getMinecraftVersion());
    }
    private void events() {
        new BlockDropItem();
        new EntityDeath();
        new PlayerJoin();
    }
    public UpdateChecker getUpdateChecker() {
        return updateChecker;
    }
    public ScheduleHandler getScheduleHandler() {
        return scheduleHandler;
    }
    public MaterialHandler getMaterialHandler() {
        return materialHandler;
    }
    public Message getMessage() {
        return message;
    }
    public BukkitScheduler getBukkitScheduler() {
        return bukkitScheduler;
    }
    public PluginManager getManager() {
        return manager;
    }
    public static AutoPick getInstance() {
        return instance;
    }
    public String name() {
        return getDescription().getName();
    }
    public String version() {
        return getDescription().getVersion();
    }
    public String getMinecraftVersion() {
        return getServer().getBukkitVersion();
    }
    public String getMinecraftProvider() {
        return getServer().getName();
    }
    public void sendInfo(String message) {
        getLogger().info(message);
    }
    public void sendWarning(String message) {
        getLogger().warning(message);
    }
}