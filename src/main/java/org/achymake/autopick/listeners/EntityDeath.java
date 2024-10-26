package org.achymake.autopick.listeners;

import org.achymake.autopick.AutoPick;
import org.achymake.autopick.handlers.MaterialHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.PluginManager;

public class EntityDeath implements Listener {
    private AutoPick getInstance() {
        return AutoPick.getInstance();
    }
    private MaterialHandler getMaterials() {
        return getInstance().getMaterialHandler();
    }
    private PluginManager getManager() {
        return getInstance().getManager();
    }
    public EntityDeath() {
        getManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(EntityDeathEvent event) {
        var entity = event.getEntity();
        if (entity instanceof Player)return;
        if (!(entity.getKiller() instanceof Player player))return;
        var itemStack = player.getInventory().getItemInMainHand();
        if (!getMaterials().hasEnchantment(itemStack))return;
        getMaterials().giveItems(player, event.getDrops());
        event.getDrops().clear();
    }
}