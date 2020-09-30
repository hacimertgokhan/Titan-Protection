package com.airswiss.titan.titan.TitanAnti;

import com.airswiss.titan.titan.Titan;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.Listener;

public class UserSteal implements Listener {

    public Titan pl;

    public UserSteal(Titan pl) {
        this.pl=pl;
    }

    String c(String c) {
        c = c.replace("&", "§");
        return c;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        Player player = Bukkit.getServer().getPlayerExact(event.getName().toLowerCase());
        ConfigurationSection Messagess = pl.getConfig().getConfigurationSection("Messages");
        if (player != null) {
            if (player.isOnline()) {
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, this.c(Messagess.getString("UserStealKick")));
            }
        }
    }

}
