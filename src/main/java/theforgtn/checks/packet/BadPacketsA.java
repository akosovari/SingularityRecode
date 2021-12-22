package theforgtn.checks.packet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.Actions;
import theforgtn.data.ConfigFile;
import theforgtn.Main;
import theforgtn.data.PlayerData;
import theforgtn.events.MoveEvents;

import static java.lang.Math.abs;

public class BadPacketsA extends Actions {



    public BadPacketsA(String name, boolean enabled, int max) {
        super(name, enabled, max);
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if(!enabled || !Main.getInstance().enabled){ return;}

        if(Math.abs(player.getLocation().getPitch()) > 90){
            flag(player);
            if(ConfigFile.BPA_cancel){
                player.teleport(new Location(player.getWorld(), data.USP_X, data.USP_Y, data.USP_Z, data.USP_YAW, data.USP_PITCH));
            }
            }
        if(Math.abs(player.getLocation().getPitch()) < -90){
            flag(player);
            if(ConfigFile.BPA_cancel){
                player.teleport(new Location(player.getWorld(), data.USP_X, data.USP_Y, data.USP_Z, data.USP_YAW, data.USP_PITCH));
            }
        }
    }
}