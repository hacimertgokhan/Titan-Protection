package com.airswiss.titan.titan.TitanAnti.Crash;

import com.airswiss.titan.titan.Titan;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class A4 implements Listener {

    public static char BAD_CHARACTER = '\u0307';

    public static boolean containsBadCharacter(String string) {
        return string.contains(String.valueOf(BAD_CHARACTER));
    }

    public Titan pl;

    public A4(Titan pl) {
        this.pl=pl;
    }

    private boolean checkItem(ItemStack item) {
        if (item == null) return false;
        if (item.getItemMeta() == null) return false;

        String itemName = item.getItemMeta().getDisplayName();
        List<String> lore = item.getItemMeta().getLore();

        if (itemName != null && containsBadCharacter(itemName)) return true;

        if (lore == null) return false;

        for (String line : lore) {
            if (line != null && containsBadCharacter(line)) return true;
        }
        return false;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent event) {
        ConfigurationSection Messages = pl.getConfig().getConfigurationSection("AntiCrash");
        if (!(event.getInventory() instanceof AnvilInventory)) return;

        HumanEntity player = event.getWhoClicked();
        InventoryView view = event.getView();
        int rawSlot = event.getRawSlot();

        if (rawSlot == view.convertSlot(rawSlot) && rawSlot == 4) {
            ItemStack item = event.getCurrentItem();
            if (checkItem(item)) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getString("InventoryClickEvent.cancelMessage")));
            }
        }
    }
}
