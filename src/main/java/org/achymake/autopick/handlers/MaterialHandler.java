package org.achymake.autopick.handlers;

import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class MaterialHandler {
    public boolean hasEnchantment(ItemStack itemStack) {
        return itemStack.getItemMeta().hasEnchant(Enchantment.getByName("auto_pick"));
    }
    public void giveItem(Player player, ItemStack itemStack) {
        if (itemStack != null) {
            if (Arrays.asList(player.getInventory().getStorageContents()).contains(null)) {
                player.getInventory().addItem(itemStack);
            } else dropItemStack(player.getLocation(), itemStack);
        }
    }
    public void giveItems(Player player, List<ItemStack> itemStacks) {
        for (var itemStack : itemStacks) {
            giveItem(player, itemStack);
        }
    }
    public void dropItemStack(Location location, ItemStack itemStack) {
        var world = location.getWorld();
        if (world != null) {
            world.dropItem(location, itemStack);
        }
    }
}