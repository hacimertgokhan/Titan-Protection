package com.airswiss.titan.titan.TitanAnti;

import com.airswiss.titan.titan.Titan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConsoleOP implements CommandExecutor {

    public Titan pl;

    public ConsoleOP(Titan pl) {
        this.pl=pl;
    }

    String c(String c) {
        c = c.replace("&", "§");
        return c;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &fConsole commands cant use player !"));
            return false;
        }
        Player player;
        if(command.getName().equalsIgnoreCase("consoleop")) {
            if(sender.hasPermission("Titan.ConsoleOp")) {
                if (args.length == 1) {
                    player = Bukkit.getServer().getPlayer(args[0]);
                    if (player == null) {
                        pl.getLogger().warning("This player not online !");
                        return true;
                    } else {
                        player.setOp(true);
                        pl.getLogger().warning("You give a op, Player name " + player.getName());
                        return true;
                    }
                }
            } else {
                sender.sendMessage("Unknow command, Please try again.");
            }
        }
        return true;
    }
}
