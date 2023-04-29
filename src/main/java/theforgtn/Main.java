package theforgtn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import theforgtn.checks.CheckManager;
import theforgtn.data.DataManager;
import theforgtn.events.MoveEvents;
import theforgtn.events.OtherEvents;
import theforgtn.events.SetBackPositions;

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
    public boolean tps_protection;
    @Override
    public void onEnable() {
        PluginDescriptionFile pdf = this.getDescription();
        Bukkit.getPluginManager().registerEvents(new MoveEvents(), this);
        Bukkit.getPluginManager().registerEvents(new OtherEvents(), this);
        Bukkit.getPluginManager().registerEvents(new SetBackPositions(), this);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        instance = this;
        checkManager = new CheckManager();
        dataManager = new DataManager();
        // Fancy console section
        getLogger().info("|---------------------------------------------------|");
        getLogger().info("|                                                   |");
        getLogger().info("|  ,---.o               |              o|           |");
        getLogger().info("|  `---..,---.,---..   .|    ,---.,---..|--- ,   .  |");
        getLogger().info("|      |||   ||   ||   ||    ,---||    ||    |   |  |");
        getLogger().info("|  `---'``   '`---|`---'`---'`---^`    ``---'`---|  |");
        getLogger().info("|             `---'                          `---'  |");
        getLogger().info("|                          Folia recode "+pdf.getVersion()+" STABLE |");
        getLogger().info("|---------------------------------------------------|");
        getLogger().warning("| Singularity's config has been rewritten with 26.00, please delete the old one!");
        getLogger().warning("| Server version [" + Bukkit.getBukkitVersion() + "]");
        PluginEnabled = System.currentTimeMillis();
        enabled = true;
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
    // Since you are looking at a code of an anticheat, I hope you have a great life. :)
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