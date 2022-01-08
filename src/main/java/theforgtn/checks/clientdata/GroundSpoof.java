package theforgtn.checks.clientdata;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.Main;
import theforgtn.ReactWith;
import theforgtn.data.PlayerData;

import java.util.HashMap;

import static java.lang.Math.abs;


public class GroundSpoof extends ReactWith {
    public GroundSpoof(String name, boolean enabled, int max) { super(name, enabled, max); }
    HashMap<String, Boolean> lastValues = new HashMap<String, Boolean>();
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        // Yeah this random dude really uses a hashmap for a simple groundspoof check.
        // Btw I use arch
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if(!enabled || !Main.getInstance().enabled || data.inCreative || data.SlimePosition || data.onBoat || player.isInsideVehicle() || player.getLocation().getY() < 0){ return; }
        // Number of checked positions.
        int max_count = 3;
        if(data.GroundSpoof_tick_id >= 0 && data.GroundSpoof_tick_id <= max_count) { data.GroundSpoof_tick_id = data.GroundSpoof_tick_id + 1; } else { data.GroundSpoof_tick_id = 0; }
        lastValues.put(player + String.valueOf(data.GroundSpoof_tick_id), (abs(abs(player.getLocation().getBlockY()) - data.USP_Y) > 2) && (data.NearGround != data.clientGround));
        if((lastValues.get(player + String.valueOf(data.GroundSpoof_tick_id)) && (lastValues.get(player + String.valueOf(Math.round(data.GroundSpoof_tick_id/2))) && (lastValues.get(player + String.valueOf(1)) && data.GroundSpoof_tick_id >= max_count)))){
            flag(player,0);
            SetBack(player, 0);
        }
    }
}

