package com.airswiss.titan.titan.TitanManager;

import com.airswiss.titan.titan.Titan;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class FakeKick implements CommandExecutor {

    public Titan pl;

    public FakeKick(Titan pl) {
        this.pl=pl;
    }

    String c(String c) {
        c = c.replace("&", "§");
        return c;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection Messagess = pl.getConfig().getConfigurationSection("Messages");
        ConfigurationSection Messages = pl.getConfig().getConfigurationSection("Variables");
        if(!(sender instanceof Player)) {
            sender.sendMessage("You cant do that !");
            return false;
        }
        Player p = (Player) sender;
        String cname = Messages.getString("Name*Variables");
        String name = p.getName();
        if (command.getName().equalsIgnoreCase("TFKick")) {
            if(sender.hasPermission("Titan.FakeKick")) {
                if (args.length == 0) {
                    p.sendMessage(this.c("&3&lTITAN &8&l▸ &c/TFKick (Playername)"));
                    return true;
                }
                Player target = (Bukkit.getServer().getPlayer(args[0]));
                if (target == null) {
                    p.sendMessage(this.c(Messagess.getString("Notonline").replace(cname, name)));
                    return true;
                } else {
                    target.kickPlayer(this.c("&cYou Disconnect From Server Unknow Problem"));
                    return true;
                }
            } else {
                sender.sendMessage("Unknow command, Please try another command.");
            }
        }
        return true;
    }
}
