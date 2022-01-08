package theforgtn.checks.movement;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.ReactWith;
import theforgtn.data.ConfigFile;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class VerticalMovement extends ReactWith {
    public VerticalMovement(String name, boolean enabled, int max) { super(name, enabled, max); }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if (!enabled || data.withElytra || data.inCreative || player.getAllowFlight() || player.isInsideVehicle() || player.isInWater() || player.isGliding() || data.usingRiptide || data.clientGround) { return; }
        // STEP
        if(data.deltaY > 0.5 && data.velYTicks > 0.1){
            flag(player,0);
            SetBack(player,3);
        }
        // Position
        if (abs(abs(player.getLocation().getX()) - Math.abs(data.USP_X)) > 1 || abs(abs(player.getLocation().getZ()) - Math.abs(data.USP_Z)) > 1) {
            if (data.deltaY == data.VTMlast_deltaY) {
                if (1000 < System.currentTimeMillis() - data.lastOnGround) {
                    flag(player,0);
                    SetBack(player,3);

                    if (data.deltaXZ == data.HMLastXZ || Math.abs(data.deltaY) < 0.1) {
                        flag(player,0);
                        SetBack(player,3);

                    }
                }
            }
            if (abs(abs(player.getLocation().getX()) - Math.abs(data.USP_X)) > 10 || abs(abs(player.getLocation().getZ()) - Math.abs(data.USP_Z)) > 10) {
                if(data.deltaXZ == data.HMLastXZ) {
                    flag(player,0);
                    SetBack(player,3);

                }
            }

            data.VTMlast_deltaY = data.deltaY;
            data.HMLastXZ = data.deltaXZ;
        }
    }
}