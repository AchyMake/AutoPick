package org.achymake.autopick.listeners;

import org.achymake.autopick.AutoPick;
import org.achymake.autopick.handlers.MaterialHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.plugin.PluginManager;

public class BlockDropItem implements Listener {
    private AutoPick getInstance() {
        return AutoPick.getInstance();
    }
    private MaterialHandler getMaterials() {
        return getInstance().getMaterialHandler();
    }
    private PluginManager getManager() {
        return getInstance().getManager();
    }
    public BlockDropItem() {
        getManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockDropItem(BlockDropItemEvent event) {
        var player = event.getPlayer();
        var itemStack = player.getInventory().getItemInMainHand();
        if (!getMaterials().hasEnchantment(itemStack))return;
        event.getItems().forEach(item -> {
            getMaterials().giveItem(player, item.getItemStack());
            item.remove();
        });
    }
}