package com.airswiss.titan.titan.TitanSubCommands;

import com.airswiss.titan.titan.Titan;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Discord2 implements CommandExecutor {

    public Titan pl;

    public Discord2(Titan pl) {
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
        if(Messages.getBoolean("Discord.Enable", true)) {
            if(command.getName().equalsIgnoreCase("Dc")) {
                sender.sendMessage(this.c("&9▇▇▇▇▇▇▇▇"));
                sender.sendMessage(this.c("&9▇&f▇▇&9▇▇&f▇▇&9▇"));
                sender.sendMessage(this.c("&9▇&f▇▇▇▇▇▇&9▇         &b&l&nDiscord&b&l:"));
                sender.sendMessage(this.c("&9▇&f▇&9▇&f▇▇&9▇&f▇&9▇"));
                sender.sendMessage(this.c("&9▇&f▇▇▇▇▇▇&9▇  &f" + Messages.getString("Discord.URL")));
                sender.sendMessage(this.c("&9▇&f▇&9▇▇▇▇&f▇&9▇"));
                sender.sendMessage(this.c("&9▇&f▇▇&9▇▇&f▇▇&9▇"));
                sender.sendMessage(this.c("&9▇▇&9▇▇▇▇▇▇"));
                return true;
            }
        } else {
            sender.sendMessage(this.c(Messagess.getString("CommandDisable")));
        }
        return true;
    }
}
