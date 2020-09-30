package com.airswiss.titan.titan.TitanAnti;

import com.airswiss.titan.titan.Titan;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Syntax implements Listener {

    public Titan pl;

    public Syntax(Titan pl) {
        this.pl=pl;
    }

    String c(String c) {
        c = c.replace("&", "§");
        return c;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        ConfigurationSection Messages = pl.getConfig().getConfigurationSection("Messages");
        if (e.getMessage().split(" ")[0].contains(":")) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(this.c(Messages.getString("BlockedMessage")));
        }
    }

}
