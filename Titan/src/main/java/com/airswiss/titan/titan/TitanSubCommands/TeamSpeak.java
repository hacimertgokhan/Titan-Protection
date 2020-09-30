package com.airswiss.titan.titan.TitanSubCommands;

import com.airswiss.titan.titan.Titan;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class TeamSpeak implements CommandExecutor {

    public Titan pl;

    public TeamSpeak(Titan pl) {
        this.pl=pl;
    }

    String c(String c) {
        c = c.replace("&", "§");
        return c;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection Messages = pl.getConfig().getConfigurationSection("SubCommands");
        ConfigurationSection Messagess = pl.getConfig().getConfigurationSection("Messages");
        if(!(sender instanceof Player)) {
            sender.sendMessage("You cant do that !");
            return false;
        }
        if(Messages.getBoolean("TeamSpeak.Enable", true)) {
            if(command.getName().equalsIgnoreCase("TeamSpeak")) {
                sender.sendMessage(this.c(Messages.getString("TeamSpeak.URL")));
                return true;
            }
        } else {
            sender.sendMessage(this.c(Messagess.getString("CommandDisable")));
        }
        return true;
    }
}
