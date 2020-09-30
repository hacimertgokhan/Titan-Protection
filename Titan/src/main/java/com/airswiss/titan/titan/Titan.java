package com.airswiss.titan.titan;

import com.airswiss.titan.titan.TitanAnti.*;
import com.airswiss.titan.titan.TitanAnti.Crash.A1;
import com.airswiss.titan.titan.TitanAnti.Crash.A2;
import com.airswiss.titan.titan.TitanAnti.Crash.A3;
import com.airswiss.titan.titan.TitanAnti.Crash.A4;
import com.airswiss.titan.titan.TitanManager.*;
import com.airswiss.titan.titan.TitanCommands.Operators;
import com.airswiss.titan.titan.TitanCommands.TAnnouncer.TitanAnnouncerMain;
import com.airswiss.titan.titan.TitanSubCommands.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.List;

public final class Titan extends JavaPlugin {

    public File d;
    public FileConfiguration c;
    private static final int TICKS_PER_SEC = 20;
    private static final int SECS_PER_MIN = 60;

    public BukkitTask task = null;

    public List<String> messages = null;

    public boolean random = false;
    public int delay = 0;
    public String prefix = null;

    public File file;
    public FileConfiguration config;
    public PluginManager pm = Bukkit.getServer().getPluginManager();

    public void File() {
        c=getConfig();
        d=new File(getDataFolder(),"config.yml");
        saveDefaultConfig();
    }

    public void registerCommands() {
        getCommand("TitanP").setExecutor(new Operators(this));
        getCommand("Discord").setExecutor(new Discord(this));
        getCommand("Dc").setExecutor(new Discord2(this));
        getCommand("Youtube").setExecutor(new Youtube(this));
        getCommand("Yt").setExecutor(new Youtube2(this));
        getCommand("TeamSpeak").setExecutor(new TeamSpeak(this));
        getCommand("Ts").setExecutor(new TeamSpeak2(this));
        getCommand("Site").setExecutor(new WebSite2(this));
        getCommand("WebSite").setExecutor(new WebSite(this));
        getCommand("TFKick").setExecutor(new FakeKick(this));
        getCommand("TFreeze").setExecutor(new Freeze(this));
        getCommand("TunFreeze").setExecutor(new unFreeze(this));
        getCommand("TCheckban").setExecutor(new CheckBan(this));
        getCommand("TCheckOp").setExecutor(new CheckOP(this));
        getCommand("TCheckid").setExecutor(new CheckUUID(this));
        getCommand("consoleop").setExecutor(new ConsoleOP(this));
        getCommand("op").setExecutor(new OPPassword(this));
        getCommand("oppassword").setExecutor(new OPPassword(this));
        getLogger().info("Succesfuly ! Commands Enable !");
    }

    public void registerEvents() {

        pm.registerEvents(new Syntax(this), this);
        pm.registerEvents(new Playerisonline(this), this);
        pm.registerEvents(new UserSteal(this), this);
        pm.registerEvents(new A1(this), this);
        pm.registerEvents(new A2(this), this);
        pm.registerEvents(new A3(this), this);
        pm.registerEvents(new A4(this), this);
        getLogger().info("Succesfuly ! Events Enable !");
    }

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
        File();
        saveDefaultConfig();
        random = getConfig().getBoolean("TitanAnnouncer.RandomMessage");
        delay = getConfig().getInt("TitanAnnouncer.Cooldown");
        prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("TitanAnnouncer.Prefix"));
        file = new File(getDataFolder(), "config.yml");
        config = getConfig();
        getConfig().options().copyDefaults(true);
        messages = getConfig().getStringList("TitanAnnouncer.Messages");
        if (messages.isEmpty()) {
            getLogger().info("Plugin disable, Titan Announcer Messages is Empty.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else {
            for (int i = 0; i < messages.size(); i++) {
                messages.set(i, ChatColor.translateAlternateColorCodes('&', messages.get(i)));
            }
        }

        task = new TitanAnnouncerMain(this).runTaskTimer(this, 0, delay * TICKS_PER_SEC * SECS_PER_MIN);

        getLogger().info("Succesfuly ! Titan Enable !");
    }


    @Override
    public void onDisable() {
        if (task != null) {
            task.cancel();
        }
    }

}