package org.achymake.autopick;

import org.achymake.autopick.handlers.MaterialHandler;
import org.achymake.autopick.listeners.BlockDropItem;
import org.achymake.autopick.listeners.EntityDeath;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AutoPick extends JavaPlugin {
    private static AutoPick instance;
    private MaterialHandler materialHandler;
    private PluginManager manager;
    @Override
    public void onEnable() {
        instance = this;
        materialHandler = new MaterialHandler();
        manager = getServer().getPluginManager();
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
    }
    public PluginManager getManager() {
        return manager;
    }
    public MaterialHandler getMaterialHandler() {
        return materialHandler;
    }
    public static AutoPick getInstance() {
        return instance;
    }
    public void sendInfo(String message) {
        getLogger().info(message);
    }
    public String getMinecraftVersion() {
        return getServer().getBukkitVersion();
    }
    public String getMinecraftProvider() {
        return getServer().getName();
    }
}