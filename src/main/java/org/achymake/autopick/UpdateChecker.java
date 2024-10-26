package org.achymake.autopick;

import org.achymake.autopick.data.Message;
import org.achymake.autopick.handlers.ScheduleHandler;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {
    private AutoPick getInstance() {
        return AutoPick.getInstance();
    }
    private Message getMessage() {
        return getInstance().getMessage();
    }
    private ScheduleHandler getSchedule() {
        return getInstance().getScheduleHandler();
    }
    private String getName() {
        return getInstance().name();
    }
    private String getVersion() {
        return getInstance().version();
    }
    public void getUpdate(Player player) {
        if (player.hasPermission("autopick.event.join.update")) {
            getSchedule().runLater(new Runnable() {
                @Override
                public void run() {
                    getLatest((latest) -> {
                        if (!getVersion().equals(latest)) {
                            getMessage().send(player, getName() + "&6 has new update:");
                            getMessage().send(player, "-&a https://www.spigotmc.org/resources/120418/");
                        }
                    });
                }
            }, 5);
        }
    }
    public void getUpdate() {
        getSchedule().runAsynchronously(new Runnable() {
            @Override
            public void run() {
                getLatest((latest) -> {
                    if (!getVersion().equals(latest)) {
                        getInstance().sendInfo(getName() + " has new update:");
                        getInstance().sendInfo("- https://www.spigotmc.org/resources/120418/");
                    }
                });
            }
        });
    }
    public void getLatest(Consumer<String> consumer) {
        try (var inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + 120418).openStream()) {
            var scanner = new Scanner(inputStream);
            if (scanner.hasNext()) {
                consumer.accept(scanner.next());
                scanner.close();
            } else inputStream.close();
        } catch (IOException e) {
            getInstance().sendWarning(e.getMessage());
        }
    }
}