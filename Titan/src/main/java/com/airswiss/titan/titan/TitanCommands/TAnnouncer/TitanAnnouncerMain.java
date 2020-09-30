package com.airswiss.titan.titan.TitanCommands.TAnnouncer;

import com.airswiss.titan.titan.Titan;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class TitanAnnouncerMain extends BukkitRunnable {
    private Titan plugin;

    private int count = 0;
    private Random random = null;

    public TitanAnnouncerMain(Titan pl) {
        plugin = pl;
        count = 0;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        if (plugin.getConfig().getBoolean("TitanAnnouncer.Enable")) {
            String message = plugin.prefix;


            if (plugin.random) {
                count = random.nextInt(plugin.messages.size());

                message += plugin.messages.get(count);
            } else {

                message += plugin.messages.get(count);

                count++;
                if (count == plugin.messages.size()) {
                    count = 0;
                }
            }

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("Titan.Announcer.See")) {
                    p.sendMessage(message);
                }
            }
        }
    }
}
