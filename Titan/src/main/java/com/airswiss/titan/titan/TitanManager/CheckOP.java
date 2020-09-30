package com.airswiss.titan.titan.TitanManager;

import com.airswiss.titan.titan.Titan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CheckOP implements CommandExecutor {
    public CheckOP(Titan titan) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You cant do that !");
            return false;
        }
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("TCheckop")) {
            if (sender.hasPermission("Titan.CheckOP") && sender.hasPermission("Titan.Admin")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &c/TCheckop (PlayerName)"));
                    return true;
                }
                Player target = (Bukkit.getServer().getPlayer(args[0]));
                UUID uuid = target.getUniqueId();
                if (target == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &cPlayer isnt online"));
                    return true;
                } else {
                    if (target.isOp()) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &fPlayer have a op"));
                        return true;
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &fPlayer doesnt has a op"));
                        return true;
                    }
                }
            } else {
                p.sendMessage("Unknow command, Please try another command");
            }
        }
        return true;
    }
}
