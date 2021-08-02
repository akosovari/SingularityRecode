package theforgtn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import theforgtn.data.Manager;
import theforgtn.checks.CheckManager;
import theforgtn.events.MoveEvents;
import theforgtn.events.OtherEvents;

public class Main extends JavaPlugin {

    private static Main instance;
    public boolean enabled;
    public boolean native_version;
    public double version;
    private CheckManager checkManager;
    private Manager dataManager;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        checkManager = new CheckManager();
        dataManager = new Manager();
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();


        Bukkit.getPluginManager().registerEvents(new MoveEvents(), this);
        Bukkit.getPluginManager().registerEvents(new OtherEvents(), this);


        enabled = true;
        PluginDescriptionFile pdf = this.getDescription();
        getLogger().info("|---------------------------------------------------|");
        getLogger().info("|                                                   |");
        getLogger().info("|  ,---.o               |              o|           |");
        getLogger().info("|  `---..,---.,---..   .|    ,---.,---..|--- ,   .  |");
        getLogger().info("|      |||   ||   ||   ||    ,---||    ||    |   |  |");
        getLogger().info("|  `---'``   '`---|`---'`---'`---^`    ``---'`---|  |");
        getLogger().info("|             `---'                          `---'  |");
        getLogger().info("|                              Recode Version 17    |");
        getLogger().info("|---------------------------------------------------|");
        if (Bukkit.getBukkitVersion().contains("1.17")) {
            getLogger().info("| Native 1.17 server version.");
            getLogger().warning("| Combat checks are disabled in this version!");
            native_version = true;
        } else {
            getLogger().warning("   | Singularity may not be compatible with your server version! [" + Bukkit.getBukkitVersion() + "]");
            getLogger().warning("   | Combat checks are disabled in this version!");
            native_version = false;
        }

    }

    public void onDisable() {
        enabled = false;
        getLogger().info("All checks are disabled!");
        HandlerList.unregisterAll(this);
        Bukkit.getScheduler().cancelTasks(this);
    }

    public CheckManager getCheckManager() {
        return checkManager;
    }


    public Manager getDataManager() {
        return dataManager;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("singularity")) {
            PluginDescriptionFile pdf = this.getDescription();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5⌛&8] &7Singularity " + pdf.getVersion() + " is running. You are able see the violation flags with singularity.verbose permission."));

        }
        return false;
    }


}