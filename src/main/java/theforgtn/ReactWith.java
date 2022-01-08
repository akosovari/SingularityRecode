package theforgtn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import theforgtn.data.ConfigFile;
import theforgtn.data.PlayerData;

import java.util.Map;
import java.util.WeakHashMap;

import static org.bukkit.Bukkit.getLogger;

public abstract class ReactWith implements Listener {
    public Map<Player, Integer> violations = new WeakHashMap<>();
    protected String name;
    protected boolean enabled;
    protected int max;
    public ReactWith(String name, boolean enabled, int max) {
        this.name = name;
        this.enabled = enabled;
        this.max = max;
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }
    // Flag system
    protected void flag(Player player, int type, String... information) {
        // Movement flag handle

            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player.getPlayer());
            // False positive system
            if(System.currentTimeMillis() - data.last_flag < 5000 && System.currentTimeMillis() - data.last_flag < 250 || !ConfigFile.movement_antifalse) {
                // Vl handler
                int violations = this.violations.getOrDefault(player, 0) + 1;
                // Logging
                if (ConfigFile.console_log) {
                    getLogger().info("[⌛] " + player.getName() + " failed check " + name + " | " + violations + "x ping " + data.ping + "ms");
                }
                // Verbose
                for (Player staff : Bukkit.getOnlinePlayers()) {
                    if (staff.hasPermission("singularity.verbose") && data.violations > 0) {
                        if (type == 0) {
                            staff.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5⌛&8] &7" + player.getName() + " &7failed &cMovement &7check! &8|&3 " + violations + "&8/&c" + max + " &8" + name));
                        }
                        if (type == 1) {
                            staff.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5⌛&8] &7" + player.getName() + " &7failed &cInteraction &7check! &8|&3 " + violations + "&8/&c" + max + " &8" + name));
                        }
                    }
                }
                // Punishments
                if (violations > max) {
                    if (ConfigFile.kick_enabled) {
                        player.kickPlayer(ChatColor.translateAlternateColorCodes('&', ConfigFile.kick_message));
                    }
                    if (ConfigFile.run_command) {
                        if (player.hasPermission("singularity.verbose")) {
                            String player_replaced = ConfigFile.command.replaceAll("%player%", player.getName()).replaceAll("%type%", name);
                            Bukkit.dispatchCommand(console, player_replaced);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5⌛&8] &cSingularity executed console command. &8"+ player_replaced));
                        } else {
                            String player_replaced = ConfigFile.command.replaceAll("%player%", player.getName()).replaceAll("%type%", name);
                            Bukkit.dispatchCommand(console, player_replaced);
                        }
                    }
                    if (player.hasPermission("singularity.verbose") && !ConfigFile.run_command) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5⌛&8] &cYou would be banned!"));
                    }
                }
                // If max vl reached revert vl to 0
                if (violations > max) {
                    violations = 0;
                }
                this.violations.put(player, violations);
                data.violations = violations;

            } else {
                // Verbose
                for (Player staff : Bukkit.getOnlinePlayers()) {
                    if (staff.hasPermission("singularity.verbose") && data.violations > 0) {
                        if (ConfigFile.debug) {
                            staff.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5⌛&8] &8" + "&8Possible false positive is dettected! Check: " + name));
                        }
                    }
                }
            }
            data.last_flag = System.currentTimeMillis();


    }
    // SetBack System
    protected void SetBack(Player player, int type) {
        if(ConfigFile.movement_prevent) {
            PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player.getPlayer());
            if(System.currentTimeMillis() - data.last_setback < 5000 && System.currentTimeMillis() - data.last_setback < 250 || !ConfigFile.movement_antifalse) {
                if (type == 0) {
                    // Normal setback
                    player.teleport(new Location(player.getWorld(), data.SetBackX, data.SetBackY, data.SetBackZ, data.USP_YAW, data.USP_PITCH));
                }
                if (type == 1) {
                    // Vehicle setback
                    if (player.getLocation().getY() - player.getWorld().getHighestBlockYAt(player.getLocation()) > 0) {
                        player.teleport(new Location(player.getWorld(), player.getLocation().getX(), player.getWorld().getHighestBlockYAt(player.getLocation()) + 2, player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch()));
                    } else {
                        player.teleport(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch()));
                    }
                }
            }
            if (type == 3) {
                // NoFalse
                player.teleport(new Location(player.getWorld(), data.SetBackX, data.SetBackY, data.SetBackZ, data.USP_YAW, data.USP_PITCH));
            }
            // Past setback
            data.last_setback = System.currentTimeMillis();
        }
    }
}
