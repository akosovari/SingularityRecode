package theforgtn;

import io.netty.channel.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import theforgtn.checks.CheckManager;
import theforgtn.checks.packet.PacketEvents;
import theforgtn.data.DataManager;
import theforgtn.events.MoveEvents;
import theforgtn.events.OtherEvents;

public class Main extends JavaPlugin {
    private static Main instance;
    public boolean enabled;
    public boolean native_version;
    private CheckManager checkManager;
    private DataManager dataManager;
    public static Main getInstance() {
        return instance;
    }
    public static double PluginEnabled;
    public double tickTime;
    public double lastTick;
    public boolean tps_protection;
    @Override
    public void onEnable() {
        PluginDescriptionFile pdf = this.getDescription();
        Bukkit.getPluginManager().registerEvents(new PacketEvents(), this);
        Bukkit.getPluginManager().registerEvents(new MoveEvents(), this);
        Bukkit.getPluginManager().registerEvents(new OtherEvents(), this);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        instance = this;
        checkManager = new CheckManager();
        dataManager = new DataManager();
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                tickTime = (System.currentTimeMillis() - lastTick)/80;
                tps_protection = tickTime > 51;
                lastTick = System.currentTimeMillis();
            }
        }, 20L, 80L);
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                // Fancy console section
                getLogger().info("|---------------------------------------------------|");
                getLogger().info("|                                                   |");
                getLogger().info("|  ,---.o               |              o|           |");
                getLogger().info("|  `---..,---.,---..   .|    ,---.,---..|--- ,   .  |");
                getLogger().info("|      |||   ||   ||   ||    ,---||    ||    |   |  |");
                getLogger().info("|  `---'``   '`---|`---'`---'`---^`    ``---'`---|  |");
                getLogger().info("|             `---'                          `---'  |");
                getLogger().info("|                       Recode Version "+pdf.getVersion()+" STABLE |");
                getLogger().info("|---------------------------------------------------|");
                if (Bukkit.getBukkitVersion().contains("1.17") || Bukkit.getBukkitVersion().contains("1.18")) {
                    getLogger().info("| Successfully enabled packet check. [" + Bukkit.getBukkitVersion() + "]");
                    native_version = true;
                } else {
                    getLogger().warning("| Singularity failed to enable packet check. Unsupported version! [" + Bukkit.getBukkitVersion() + "]");
                    native_version = false;
                }
                PluginEnabled = System.currentTimeMillis();
                enabled = true;
            }
        }, 40L);
    }
    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        Bukkit.getScheduler().cancelTasks(this);
        enabled = false;
        getLogger().info("All checks are disabled!");
    }

    public DataManager getDataManager() { return dataManager; }
    // Hello there!
    // Since you are looking a code of an anticheat, I hope you have a great life. :)
    // Jokes aside, prepare for your biggest fears, and look, this abnormal dude is uses a boolean as a command listener. :)
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        PluginDescriptionFile pdf = this.getDescription();
        if (cmd.getName().equalsIgnoreCase("singularity")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5⌛&8] &7Singularity " + pdf.getVersion() + " is running. You are able see the violation flags with singularity.verbose permission."));
            return true;
        }
        return false;
    }
}