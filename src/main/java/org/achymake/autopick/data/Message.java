package org.achymake.autopick.data;

import org.achymake.autopick.AutoPick;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Message {
    private AutoPick getInstance() {
        return AutoPick.getInstance();
    }
    public void send(Player player, String message) {
        player.sendMessage(addColor(message));
    }
    public String addColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public void send(ConsoleCommandSender consoleCommandSender, String message) {
        consoleCommandSender.sendMessage(message);
    }
}