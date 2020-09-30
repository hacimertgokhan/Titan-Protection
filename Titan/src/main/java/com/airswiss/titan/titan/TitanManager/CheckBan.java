package com.airswiss.titan.titan.TitanManager;

import com.airswiss.titan.titan.Titan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CheckBan implements CommandExecutor {
    public CheckBan(Titan titan) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You cant do that !");
            return false;
        }
        Player p = (Player) sender;
        if(command.getName().equalsIgnoreCase("TCheckban")) {
            if(sender.hasPermission("Titan.CheckBan") && sender.hasPermission("Titan.Admin")) {
                if(args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &c/TCheckban (PlayerName)"));
                    return true;
                }
                Player target = (Bukkit.getServer().getPlayer(args[0]));
                if (target == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &cPlayer isnt online"));
                    return true;
                } else {
                    if(target.isBanned()) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &fPlayer have a ban"));
                        return true;
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &fPlayer doesnt has a ban"));
                        return true;
                    }
                }
            } else {
                p.sendMessage("Unknow command, Please try another command");
            }
        }
        return false;
    }
}
