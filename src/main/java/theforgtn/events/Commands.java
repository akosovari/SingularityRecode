package theforgtn.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import theforgtn.Actions;
import theforgtn.Main;
import theforgtn.checks.clientdata.GroundSpoof;
import theforgtn.checks.interactions.BlockReach;
import theforgtn.checks.interactions.HitReach;
import theforgtn.checks.movement.*;
import theforgtn.checks.packet.BadPacketsA;
import theforgtn.data.ConfigFile;
import theforgtn.data.PlayerData;

import java.util.List;

import static theforgtn.Main.*;

public class Commands implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {



        String message = event.getMessage();
        Player player = event.getPlayer();
        PlayerData data = getInstance().getDataManager().getDataPlayer(player);
        if(message.startsWith("/singularity")) {
            if(player.hasPermission("singularity.admin")){
                if(message.startsWith("/singularity verbose")) {
                    if(data.verbose){
                        data.verbose = Boolean.FALSE;
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5⌛&8] &7Disabled verbose!"));
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5⌛&8] &7Enabled verbose!"));
                        data.verbose = Boolean.TRUE;
                    }
                    return;
                }
                if(message.startsWith("/singularity help")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5⌛&8] &7Available commands:"));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &8| &7/singularity verbose - realtime violation flags"));
                    return;
                }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5⌛&8] &7Use /singularity help to get a list of available commands!"));
            }
            else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5⌛&8] &7This server is running Singularity. Access admin commands with singularity.admin permission!"));
            }
        }
    }
}
