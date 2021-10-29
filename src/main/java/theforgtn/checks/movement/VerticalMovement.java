package theforgtn.checks.movement;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import theforgtn.Actions;
import theforgtn.data.ConfigFile;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class VerticalMovement extends Actions {



    public VerticalMovement(String name, boolean enabled, boolean punishable, int max) {
        super(name, enabled, punishable, max);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        Material mb = event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(event.getPlayer());
        Vector v = event.getPlayer().getVelocity();

        if (!ConfigFile.IRP_enabled || data.inCreative || event.getPlayer().getAllowFlight() || event.getPlayer().isInsideVehicle() || data.ground || event.getPlayer().isGliding()) {
            return;
        }
        if (event.getPlayer().isInWater() || abs(abs(event.getPlayer().getLocation().getBlockX()) - Math.abs(data.USP_X)) > 1 || abs(abs(event.getPlayer().getLocation().getBlockZ()) - Math.abs(data.USP_Z)) > 1) {

            if (data.deltaY == data.VTMlast_deltaY) {
                if(1000 > System.currentTimeMillis() - data.lastFlag){
                    flag(event.getPlayer());
                    if(ConfigFile.VRTMovement_Setback){
                        v.setY(ConfigFile.pushdown_velo);
                        event.getPlayer().setVelocity(v);
                        if(100 > System.currentTimeMillis() - data.lastFlag) {
                            event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), data.USP_X, data.USP_Y, data.USP_Z, data.USP_YAW, data.USP_PITCH));
                        }
                    }
                }
                data.lastFlag = System.currentTimeMillis();
            }
            data.VTMlast_deltaY = data.deltaY;

        }
    }
}