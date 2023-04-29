package theforgtn.checks.interactions;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import theforgtn.Actions;
import theforgtn.Main;
import theforgtn.data.ConfigFile;
import theforgtn.data.PlayerData;


import static java.lang.Math.abs;

public class HitReach extends Actions {
    public HitReach(String name, boolean enabled, int max) { super(name, enabled, max); }
    // So we arrived to the part of your life full of your biggest fears.
    // Enjoy, I do not really know how I intended this part to work either.
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if(!enabled || !Main.getInstance().enabled || !(event.getDamager() instanceof Player)){ return; }
        org.bukkit.entity.Player player = ((Player) event.getDamager()).getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        //Bukkit.broadcastMessage("Hit dist " + player.getLocation().distance(event.getEntity().getLocation()));
        if(player.getEyeLocation().distance(event.getEntity().getLocation()) > 3.8){
            flag(player,1);
            if(ConfigFile.interaction_intercept_enabled) {
                event.setCancelled(true);
            }
        }
    }
}
