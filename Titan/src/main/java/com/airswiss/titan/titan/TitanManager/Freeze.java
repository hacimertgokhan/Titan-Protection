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
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;


public class Freeze implements CommandExecutor, Listener  {

    public Titan pl;

    public Freeze(Titan pl) {
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
        if(command.getName().equalsIgnoreCase("TFreeze")) {
            if(sender.hasPermission("Titan.Freeze") && sender.hasPermission("Titan.Admin")) {
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
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999, 255));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("TFreeze.playerFreezed") + target.getName()));
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("TFreeze.youFreezed")));
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("TFreeze.noPerm")));
            }
        }
        return true;
    }
}
