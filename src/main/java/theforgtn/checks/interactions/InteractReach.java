package theforgtn.checks.interactions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import theforgtn.Actions;
import theforgtn.Main;
import theforgtn.data.ConfigFile;
import theforgtn.data.PlayerData;


import static java.lang.Math.abs;

public class InteractReach extends Actions {
    public InteractReach(String name, boolean enabled, int max) { super(name, enabled, max); }
    // So we arrived to the part of your life full of your biggest fears.
    // Enjoy, I do not really know how I intended this part to work either.
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(!enabled || !Main.getInstance().enabled){ return; }
        try {
            org.bukkit.entity.Player player = event.getPlayer();
            PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
            if(player.getEyeLocation().distance(event.getClickedBlock().getLocation()) > 6){
                flag(player,1);
                if(ConfigFile.interaction_intercept_enabled) {
                    event.setCancelled(true);
                }
            }
        }catch (Exception e){
            if(ConfigFile.debug){
                Main.getInstance().getLogger().warning("| Generated an exception [" + e.getCause() + "]");
            }
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        if(!enabled || !Main.getInstance().enabled){ return; }
        try {
            org.bukkit.entity.Player player = event.getPlayer();
            PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
            if (player.getEyeLocation().distance(event.getRightClicked().getLocation()) > 6) {
                flag(player, 1);
                if (ConfigFile.interaction_intercept_enabled) {
                    event.setCancelled(true);
                }
            }
        } catch (Exception e){
            if(ConfigFile.debug){
                Main.getInstance().getLogger().warning("| Generated an exception [" + e.getCause() + "]");
            }
        }
    }
}