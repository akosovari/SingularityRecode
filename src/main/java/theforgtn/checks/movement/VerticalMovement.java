package theforgtn.checks.movement;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.Actions;
import theforgtn.data.ConfigFile;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class VerticalMovement extends Actions {
    public VerticalMovement(String name, boolean enabled, int max) { super(name, enabled, max); }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if (!enabled || data.withElytra || data.inCreative || player.getAllowFlight() || player.isInsideVehicle() || player.isInWater() || player.isGliding() || data.usingRiptide || data.clientGround) { return; }
        if (abs(abs(player.getLocation().getX()) - Math.abs(data.USP_X)) > 1 || abs(abs(player.getLocation().getZ()) - Math.abs(data.USP_Z)) > 1) {
            if (data.deltaY == data.VTMlast_deltaY) {
                if (1000 < System.currentTimeMillis() - data.lastOnGround) {
                    flag(player);
                    if (ConfigFile.VRTMovement_Setback) {
                        player.teleport(new Location(event.getPlayer().getWorld(), data.SetBackX, data.SetBackY, data.SetBackZ, data.USP_YAW, data.USP_PITCH));
                    }
                    if (data.deltaXZ == data.HMLastXZ || Math.abs(data.deltaY) < 0.1) {
                        flag(player);
                        if (ConfigFile.VRTMovement_Setback) {
                            player.teleport(new Location(player.getWorld(), data.SetBackX, data.SetBackY, data.SetBackZ, data.USP_YAW, data.USP_PITCH));
                        }
                    }
                } data.lastFlag = System.currentTimeMillis();
            }
            if (abs(abs(player.getLocation().getX()) - Math.abs(data.USP_X)) > 10 || abs(abs(player.getLocation().getZ()) - Math.abs(data.USP_Z)) > 10) {
                if(data.deltaXZ == data.HMLastXZ) {
                    flag(player);
                    if (ConfigFile.VRTMovement_Setback) {
                        player.teleport(new Location(player.getWorld(), data.SetBackX, data.SetBackY, data.SetBackZ, data.USP_YAW, data.USP_PITCH));
                    }
                }
            }

            data.VTMlast_deltaY = data.deltaY;
            data.HMLastXZ = data.deltaXZ;
        }
    }
}