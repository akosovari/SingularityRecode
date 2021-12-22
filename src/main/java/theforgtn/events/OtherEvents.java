package theforgtn.events;

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
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player.getPlayer());
        data.USP_X = data.SetBackX = data.SlimeX = data.TBSpeedX = (float) player.getLocation().getX();
        data.USP_Y = data.SetBackY = (float) player.getLocation().getY();
        data.USP_Z = data.SetBackZ = data.SlimeZ = data.TBSpeedZ = (float) player.getLocation().getZ();
        data.VTMlast_deltaY = data.deltaY;


        data.elyChunkX = data.elyX = player.getLocation().getX();
        data.elyChunkY = data.elyY =player.getLocation().getY();
        data.elyChunkZ = data.elyZ = player.getLocation().getZ();
        data.elyLastdeltaXZ = 0;
        data.elyLastDeltaY = 0;

    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void respawn(PlayerRespawnEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player.getPlayer());
        data.USP_X = data.SetBackX = data.SlimeX = data.TBSpeedX = (float) player.getLocation().getX();
        data.USP_Y = data.SetBackY = (float) player.getLocation().getY();
        data.USP_Z = data.SetBackZ = data.SlimeZ = data.TBSpeedZ = (float) player.getLocation().getZ();
        data.VTMlast_deltaY = data.deltaY;


        data.elyChunkX = data.elyX = player.getLocation().getX();
        data.elyChunkY = data.elyY =player.getLocation().getY();
        data.elyChunkZ = data.elyZ = player.getLocation().getZ();
        data.elyLastdeltaXZ = 0;
        data.elyLastDeltaY = 0;
    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void onRiptide(PlayerRiptideEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player.getPlayer());
        Main.getInstance().getDataManager().add(player);
        data.usingRiptide = true;

        data.elyChunkX = data.elyX = player.getLocation().getX();
        data.elyChunkY = data.elyY =player.getLocation().getY();
        data.elyChunkZ = data.elyZ = player.getLocation().getZ();
        data.elyLastdeltaXZ = 0;
        data.elyLastDeltaY = 0;
    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void onTeleport(PlayerTeleportEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player.getPlayer());

        data.teleportedByPlugin = event.getCause() == PlayerTeleportEvent.TeleportCause.PLUGIN;
        data.lastTeleport = System.currentTimeMillis();
        data.USP_X = data.SetBackX = data.SlimeX = data.TBSpeedX = (float) event.getTo().getX();
        data.USP_Y = data.SetBackY = (float) event.getTo().getY();
        data.USP_Z = data.SetBackZ = data.SlimeZ = data.TBSpeedZ = (float) event.getTo().getZ();
        //TimeBased speed
        data.TBSpeedX = (float) event.getTo().getX();
        data.TBSpeedZ= (float) event.getTo().getZ();
        data.TimeBasedSpeed = System.currentTimeMillis();
        //DeltaY
        data.VTMlast_deltaY = data.deltaY;

    }
}