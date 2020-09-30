package com.airswiss.titan.titan.TitanAnti.Crash;

import com.airswiss.titan.titan.Titan;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class A1 implements Listener {

    public static char BAD_CHARACTER = '\u0307';

    public static boolean containsBadCharacter(String string) {
        return string.contains(String.valueOf(BAD_CHARACTER));
    }

    public Titan pl;

    public A1(Titan pl) {
        this.pl=pl;
    }


    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent event) {
        ConfigurationSection Messages = pl.getConfig().getConfigurationSection("AntiCrash");
        ConfigurationSection Messagess = pl.getConfig().getConfigurationSection("Messages");
        Player player = event.getPlayer();
        if (containsBadCharacter(event.getMessage())) {
            event.setCancelled(true);
            if (Messages.getBoolean("PlayerChatMessageCrash.Kick")) {
                player.kickPlayer(ChatColor.translateAlternateColorCodes('&', Messages.getString("PlayerChatMessageCrash.kickMessage")));
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getString("PlayerChatMessageCrash.cancelMessage")));
            }
        }
    }
}
