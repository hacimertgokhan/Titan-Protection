package com.airswiss.titan.titan.TitanAnti;

import com.airswiss.titan.titan.Titan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class OPPassword implements CommandExecutor {
    public final ArrayList<String> pplayer = new ArrayList();

    public Titan pl;

    public OPPassword(Titan pl) {
        this.pl=pl;
    }

    String c(String c) {
        c = c.replace("&", "§");
        return c;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if (cmd.getName().equalsIgnoreCase("op")) {
            if (args.length == 1) {
                Player player = (Bukkit.getServer().getPlayer(args[0]));
                if (player == null) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &fCant found player"));
                    return true;
                } else {
                    if (sender.hasPermission("Titan.OPSECURE") || sender.isOp()) {
                        this.pplayer.add(player.getName());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &fSent op request " + player.getName()));
                        System.out.println(sender.getName() + " sent an OP request to " + player.getName());
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &fFirst, You should write password "));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &f/oppassword <Password>"));
                        return true;
                    }
                }

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &fYou dont have a permission ! "));
            } else if (args.length == 3 && args[0].equalsIgnoreCase("changepass")) {
                if (!args[1].equalsIgnoreCase(pl.getConfig().getString("OPPassword"))) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &fIncorrect password "));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &f/op changepass [current-pass] [new-pass]"));
                    return true;
                }

                if (sender.hasPermission("Titan.OPSECURE") || sender.isOp()) {
                    pl.getConfig().set("OPPassword", args[2]);
                    pl.saveConfig();
                    pl.reloadConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &fChange succesfuly, New password; " + args[2]));
                    return true;
                }
            }
        }

        if (cmd.getName().equalsIgnoreCase("oppassword") && args.length == 1) {
            Player player = (Player)sender;
            if (this.pplayer.contains(player.getName())) {
                if (args[0].equalsIgnoreCase(pl.getConfig().getString("OPPassword"))) {
                    player.setOp(true);
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &fYou are be Operator."));
                    System.out.println(player.getName() + " is now OP!");
                    System.out.println(player.getName() + "'s IP Adress: " + player.getAddress().getAddress().getHostAddress());
                    return true;
                }

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &fWrong password "));
                this.pplayer.remove(player.getName());
                return true;
            }

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&lTITAN &8&l▸ &fHave no pending request "));
        }

        return true;
    }
}
