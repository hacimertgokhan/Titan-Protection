package com.airswiss.titan.titan.TitanManager;

import com.airswiss.titan.titan.Titan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;

import java.util.UUID;


public class unFreeze implements CommandExecutor, Listener  {

    public Titan pl;

    public unFreeze(Titan pl) {
        this.pl=pl;
    }

    String c(String c) {
        c = c.replace("&", "§");
        return c;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You cant do that !");
            return false;
        }
        Player p = (Player) sender;
        if(command.getName().equalsIgnoreCase("TunFreeze")) {
            if(sender.hasPermission("Titan.unFreeze") && sender.hasPermission("Titan.Admin")) {
                if(args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("TFreeze.WrongUsing")));
                    return true;
                }
                Player target = (Bukkit.getServer().getPlayer(args[0]));
                UUID uuid = target.getUniqueId();
                if (target == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("TFreeze.isntOnline")));
                    return true;
                } else {
                    for (PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                        p.removePotionEffect(effect.getType());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("TFreeze.playerUnFreezed") + target.getName()));
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("TFreeze.youUnFreezed")));
                        return true;
                    }
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("TFreeze.noPerm")));
            }
        }
        return true;
    }
}
