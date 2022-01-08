package theforgtn.checks.interactions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import theforgtn.ReactWith;
import theforgtn.Main;
import theforgtn.data.ConfigFile;
import theforgtn.data.PlayerData;


import static java.lang.Math.abs;

public class BlockReach extends ReactWith {
    public BlockReach(String name, boolean enabled, int max) { super(name, enabled, max); }
    // So we arrived to the part of your life full of your biggest fears.
    // Enjoy, I do not really know how I intended this part to work either.
    @EventHandler
    public void BlockPlace(BlockPlaceEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if(!enabled || !Main.getInstance().enabled){ return; }
        // BlockReach
        // BlockReach
        if(data.inCreative){
            if(player.getLocation().add(0,0,0).distance(event.getBlock().getLocation()) > 7){
                flag(player,1);
                if (ConfigFile.interaction_prevent) {
                    event.setCancelled(true);
                }
            }
        } else {
            if(player.getLocation().add(0,0,0).distance(event.getBlock().getLocation()) > 6){
                flag(player,1);
                if (ConfigFile.interaction_prevent) {
                    event.setCancelled(true);
                }
            }
        }

        //Bukkit.broadcastMessage("Angle " + event.getPlayer().getLocation().getYaw() + " X: " + (event.getPlayer().getLocation().getX() - event.getBlock().getLocation().getBlockX()) + " Z: " + (event.getPlayer().getLocation().getZ() - event.getBlock().getLocation().getBlockZ()));
        // 0 - 90 +X -Z
        //if(event.getPlayer().getLocation().getYaw() > 15 && event.getPlayer().getLocation().getYaw() < 75){
        //    if(event.getPlayer().getLocation().getX() - event.getBlock().getLocation().getBlockX() < 0 || event.getPlayer().getLocation().getZ() - event.getBlock().getLocation().getBlockZ() > 0) {
        //        flag(event.getPlayer());
        //        if (ConfigFile.BBP_cancel) {
        //            event.setCancelled(true);
        //        }
        //    }
        //}
        // 90 - 180 +X +Z
        //if(event.getPlayer().getLocation().getYaw() > 105 && event.getPlayer().getLocation().getYaw() < 165){
        //    if(event.getPlayer().getLocation().getX() - event.getBlock().getLocation().getBlockX() < 0 || event.getPlayer().getLocation().getZ() - event.getBlock().getLocation().getBlockZ() < 0){
        //        flag(event.getPlayer());
        //        if (ConfigFile.BBP_cancel) {
        //            event.setCancelled(true);
        //        }
        //    }
        //}
        // -180 - -90 -X +Z
        //if(event.getPlayer().getLocation().getYaw() > -165 && event.getPlayer().getLocation().getYaw() < -75){
        //    if(event.getPlayer().getLocation().getX() - event.getBlock().getLocation().getBlockX() > 0 || event.getPlayer().getLocation().getZ() - event.getBlock().getLocation().getBlockZ() < 0) {
        //        flag(event.getPlayer());
        //        if (ConfigFile.BBP_cancel) {
        //            event.setCancelled(true);
        //        }
        //    }
        //}
        // -90 0  -X -Z
        //if(event.getPlayer().getLocation().getYaw() > -75 && event.getPlayer().getLocation().getY() < -15){
        //    if(event.getPlayer().getLocation().getX() - event.getBlock().getLocation().getBlockX() > 0 || event.getPlayer().getLocation().getZ() - event.getBlock().getLocation().getBlockZ() > 0) {
        //        flag(event.getPlayer());
        //        if (ConfigFile.BBP_cancel) {
        //            event.setCancelled(true);
        //        }
        //    }
        // }
    }
    @EventHandler
    public void BlockBreak(BlockBreakEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if(!enabled || !Main.getInstance().enabled){ return; }
        // BlockReach

        if(data.inCreative){
            if(player.getEyeLocation().distance(event.getBlock().getLocation()) > 6){
                flag(player,1);
                if (ConfigFile.interaction_prevent) {
                    event.setCancelled(true);
                }
            }
        } else {
            if(player.getEyeLocation().distance(event.getBlock().getLocation()) > 5){
                flag(player,1);
                if (ConfigFile.interaction_prevent) {
                    event.setCancelled(true);
                }
            }
        }

    }
}
