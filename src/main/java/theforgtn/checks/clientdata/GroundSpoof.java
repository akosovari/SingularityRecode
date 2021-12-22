package theforgtn.checks.clientdata;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.Actions;
import theforgtn.Main;
import theforgtn.data.ConfigFile;
import theforgtn.data.PlayerData;

import java.util.HashMap;

import static java.lang.Math.abs;


public class GroundSpoof extends Actions {
    public GroundSpoof(String name, boolean enabled, int max) { super(name, enabled, max); }
    HashMap<String, Boolean> lastValues = new HashMap<String, Boolean>();
    private int count = 0;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        // Yeah this random dude really uses a hashmap for a simple groundspoof check.
        // Btw I use arch
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if(!enabled || !Main.getInstance().enabled || data.inCreative || data.SlimePosition || data.onBoat || player.isInsideVehicle() || player.getLocation().getY() < 0){ return; }
        // Number of checked positions.
        int max_count = 3;
        if(count >= 0 && count <= max_count) { count = count + 1; } else { count = 0; }
        lastValues.put(player + String.valueOf(count), (abs(abs(player.getLocation().getBlockY()) - data.USP_Y) > 2) && (data.NearGround != data.clientGround));
        if((lastValues.get(player + String.valueOf(count)) && (lastValues.get(player + String.valueOf(Math.round(count/2))) && (lastValues.get(player + String.valueOf(1)) && count >= max_count)))){
            flag(player);
            if (data.GSP_damage < ((player.getLocation().getY() - event.getPlayer().getWorld().getHighestBlockYAt(event.getPlayer().getLocation())) - 3)) {
                data.GSP_damage = ((player.getLocation().getY() - event.getPlayer().getWorld().getHighestBlockYAt(event.getPlayer().getLocation())) - 3);
                data.GSP_damageGiven = false;
            }
            if (ConfigFile.GSP_setback) {
                player.teleport(new Location(event.getPlayer().getWorld(), data.SetBackX, data.SetBackY, data.SetBackZ, data.USP_YAW, data.USP_PITCH));
            }
        }
        if (ConfigFile.GSP_damage && data.NearGround && !data.GSP_damageGiven) {
            player.damage(data.GSP_damage);
            data.GSP_damageGiven = true;
        }
    }
}

