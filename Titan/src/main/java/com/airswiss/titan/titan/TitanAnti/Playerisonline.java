package com.airswiss.titan.titan.TitanAnti;

import com.airswiss.titan.titan.Titan;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class Playerisonline implements Listener {

    public Titan pl;

    public Playerisonline(Titan pl) {
        this.pl=pl;
    }

    String c(String c) {
        c = c.replace("&", "§");
        return c;
    }

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event) {
        String username = event.getName();
        String ip = event.getAddress().getHostAddress();
        Player player = Bukkit.getPlayerExact(username);
        if (player != null && player.isOnline()) {
            if(player.hasPermission("Titan.Staff")) {
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, pl.getConfig().getString("This player already online"));
                return;
            }
        }
    }
}
