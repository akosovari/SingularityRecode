package theforgtn.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class OtherEvents implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        Main.getInstance().getDataManager().add(player);
        try {
            PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player.getPlayer());
            data.USP_X = data.SetBackX = data.SlimeX = data.TBSpeedX = (float) player.getLocation().getX();
            data.USP_Y = data.SetBackY = (float) player.getLocation().getY();
            data.USP_Z = data.SetBackZ = data.SlimeZ = data.TBSpeedZ = (float) player.getLocation().getZ();
            data.VTMlast_deltaY = data.deltaY;


            data.elyChunkX = data.elyX = player.getLocation().getX();
            data.elyChunkY = data.elyY = player.getLocation().getY();
            data.elyChunkZ = data.elyZ = player.getLocation().getZ();
            data.elyLastdeltaXZ = 0;
            data.elyLastDeltaY = 0;

            player.getScheduler().runAtFixedRate(Main.getInstance(), scheduledTask -> {
                // PlayerSpeed
                if (data.last_location != null && player.getLocation().getWorld() == data.last_location.getWorld()) {
                    if (player.getLocation() != data.last_location) {
                        Float delta_time = (float) (System.currentTimeMillis() - data.last_check) / 1000;
                        data.speed = (double) player.getLocation().distance(data.last_location) / delta_time;
                    }
                }
                data.last_check = System.currentTimeMillis();
                data.last_location = player.getLocation();
            }, null, 1L, 1L);
        } catch (Exception e){}
    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void respawn(PlayerRespawnEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player.getPlayer());
        try {
            data.USP_X = data.SetBackX = data.SlimeX = data.TBSpeedX = (float) player.getLocation().getX();
            data.USP_Y = data.SetBackY = (float) player.getLocation().getY();
            data.USP_Z = data.SetBackZ = data.SlimeZ = data.TBSpeedZ = (float) player.getLocation().getZ();
            data.VTMlast_deltaY = data.deltaY;


            data.elyChunkX = data.elyX = player.getLocation().getX();
            data.elyChunkY = data.elyY = player.getLocation().getY();
            data.elyChunkZ = data.elyZ = player.getLocation().getZ();
            data.elyLastdeltaXZ = 0;
            data.elyLastDeltaY = 0;
        }catch (Exception e){}
    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void onRiptide(PlayerRiptideEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player.getPlayer());
        Main.getInstance().getDataManager().add(player);
        try {
            data.usingRiptide = true;
            data.TBSpeedX = (float) event.getPlayer().getLocation().getX();
            data.TBSpeedZ = (float) event.getPlayer().getLocation().getZ();
            data.elyChunkX = data.elyX = player.getLocation().getX();
            data.elyChunkY = data.elyY = player.getLocation().getY();
            data.elyChunkZ = data.elyZ = player.getLocation().getZ();
            data.elyLastdeltaXZ = 0;
            data.elyLastDeltaY = 0;
        } catch (Exception e){}
    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void onTeleport(PlayerTeleportEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player.getPlayer());
        try {
            data.teleportedByPlugin = event.getCause() == PlayerTeleportEvent.TeleportCause.PLUGIN;
            data.lastTeleport = System.currentTimeMillis();
            data.USP_X = data.SetBackX = data.SlimeX = data.TBSpeedX = (float) event.getTo().getX();
            data.USP_Y = data.SetBackY = (float) event.getTo().getY();
            data.USP_Z = data.SetBackZ = data.SlimeZ = data.TBSpeedZ = (float) event.getTo().getZ();
            //TimeBased speed
            data.TBSpeedX = (float) event.getTo().getX();
            data.TBSpeedZ = (float) event.getTo().getZ();
            data.TimeBasedSpeed = System.currentTimeMillis();
            //DeltaY
            data.VTMlast_deltaY = data.deltaY;
        } catch (Exception e){}
    }
}