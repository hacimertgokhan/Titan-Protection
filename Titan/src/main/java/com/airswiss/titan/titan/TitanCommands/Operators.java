package com.airswiss.titan.titan.TitanCommands;

import com.airswiss.titan.titan.Titan;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Operators implements CommandExecutor {

    public Titan pl;

    public Operators(Titan pl) {
        this.pl=pl;
    }

    String c(String c) {
        c = c.replace("&", "§");
        return c;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection Messages = pl.getConfig().getConfigurationSection("Messages");
        if(!(sender instanceof Player)) {
            sender.sendMessage("You cant do that !");
            return false;
        }
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("TitanP")) {
            if (p.hasPermission("Titan.Admin")) {
                if (args.length == 0) {
                    p.sendMessage(this.c("&8&l▸ &3&lTITAN &8▪ &bMain Commands"));
                    p.sendMessage(this.c("  &7▸ &f/TitanP Reload &8▪ &fRefreshes the config you set."));
                    p.sendMessage(this.c("  &7▸ &f/TitanP Subcommands &8▪ &fSee a plugin sub commands."));
                    return true;
                }
                if (args[0].equalsIgnoreCase("help")) {
                    p.sendMessage(this.c("&8&l▸ &3&lTITAN &8▪ &bMain Commands"));
                    p.sendMessage(this.c("  &7▸ &f/TitanP Reload &8▪ &fRefreshes the config you set."));
                    p.sendMessage(this.c("  &7▸ &f/TitanP Subcommands &8▪ &fSee a plugin sub commands."));
                    return true;
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    pl.reloadConfig();
                    pl.saveConfig();
                    p.sendMessage(this.c(Messages.getString("Reloaded")));
                    return true;
                }
                if (args[0].equalsIgnoreCase("subcommands")) {
                    p.sendMessage(this.c("&8• &3Titan Sub Commands"));
                    p.sendMessage(this.c("  &8• &7/Discord|Dc"));
                    p.sendMessage(this.c("  &8• &7/WebSite|Site"));
                    p.sendMessage(this.c("  &8• &7/TeamSpeak|Ts"));
                    p.sendMessage(this.c("  &8• &7/Youtube|Yt"));
                    p.sendMessage("");
                    p.sendMessage(this.c("&8• &cIt will not work if you do not activate from config."));
                    return true;
                }
            } else {
                p.sendMessage(this.c(Messages.getString("Nopermission")));
            }
        }
        return true;
    }
}
