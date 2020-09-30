package com.airswiss.titan.titan.TitanAnti.Crash;

import com.airswiss.titan.titan.Titan;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class A3 implements Listener {
    public static char BAD_CHARACTER = '\u0307';

    public Titan pl;

    public A3(Titan pl) {
        this.pl=pl;
    }


    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onSignChange(SignChangeEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection Messages = pl.getConfig().getConfigurationSection("AntiCrash");
        for (String line : event.getLines()) {
            if (line.length() > Messages.getInt("SignChangeEvent.MaxSignLong")) {
                if (Messages.getBoolean("SignChangeEvent.Kick")) {
                    player.kickPlayer(ChatColor.translateAlternateColorCodes('&', Messages.getString("SignChangeEvent.kickMessage")));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getString("SignChangeEvent.cancelMessage")));
                }
            }
        }
    }
}
