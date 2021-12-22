package theforgtn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import theforgtn.checks.movement.AirFriction;
import theforgtn.data.ConfigFile;
import theforgtn.data.PlayerData;

import java.util.Map;
import java.util.WeakHashMap;

import static org.bukkit.Bukkit.getLogger;

public abstract class Actions implements Listener {
    public Map<Player, Integer> violations = new WeakHashMap<>();
    protected String name;
    protected boolean enabled;
    protected int max;
    public Actions(String name, boolean enabled, int max) {
        this.name = name;
        this.enabled = enabled;
        this.max = max;
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }
    protected void flag(Player player, String... information) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player.getPlayer());
        int violations = this.violations.getOrDefault(player,0) + 1;
        // I really should think about recreating this reset method because it sucks.
        if(System.currentTimeMillis() - data.vl_reset_time > 120000){
            data.vl_reset_time = System.currentTimeMillis();
            violations = 0;
            data.violations = violations;
            getLogger().info("[⌛] " + player.getName() + "'s vl reset.");
            return;
        }
        if(ConfigFile.console_log) {
            getLogger().info("[⌛] " + player.getName() + " suspected for " + name + " | " + violations + "x ping " + data.ping + "ms");
        }
        for (Player staff : Bukkit.getOnlinePlayers()) {
            if (staff.hasPermission("singularity.verbose") && data.violations > 0) {
                staff.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5⌛&8] &7" + player.getName() + " &7failed &c" + name + " &7check! &8|&3 " + violations + "&8/&c"+max+" &8[ " + data.ping +"ms ]"));
            }
        }
        if (violations > max) {
            if(ConfigFile.kick_enabled){
                player.kickPlayer(ChatColor.translateAlternateColorCodes('&', ConfigFile.kick_message));
            }
            if(ConfigFile.run_command) {
                if (player.hasPermission("singularity.verbose")) {
                    String player_replaced = ConfigFile.command.replaceAll("%player%", player.getName());
                    Bukkit.dispatchCommand(console, player_replaced);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5⌛&8] &cSingularity executed console command."));
                } else {
                    String player_replaced = ConfigFile.command.replaceAll("%player%", player.getName());
                    Bukkit.dispatchCommand(console, player_replaced);
                }
            }
            if (player.hasPermission("singularity.verbose") && !ConfigFile.run_command){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5⌛&8] &cYou would be banned by " + name + " check!"));
            }
        }
        if (violations > max){
            violations = 0;
        }
        this.violations.put(player, violations);
        data.violations = violations;
    }

}
