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
import static theforgtn.data.ConfigFile.IRP_Setback;

public class IrregularPositions extends Actions {
    public IrregularPositions(String name, boolean enabled, int max) { super(name, enabled, max); }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        // My first check, maybe I should optimalise it in the future.
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        Material m = player.getLocation().subtract(0, 1, 0).getBlock().getType();
        if (!enabled || !Main.getInstance().enabled || data.NearGround || data.withElytra || data.inCreative || player.getAllowFlight() || player.isInsideVehicle() || data.usingRiptide) { return; }
        if (data.ground) { data.IRP_tolerance = 1.25; }
        if (m == Material.WATER && !data.clientGround) { data.IRP_tolerance = 1.5; }
        if(data.jumpBoost){ data.IRP_tolerance = 2.5; }
        if (abs(abs(player.getLocation().getBlockX()) - Math.abs(data.USP_X)) > 2 || abs(abs(player.getLocation().getBlockY()) - Math.abs(data.USP_Y)) > data.IRP_tolerance || abs(abs(player.getLocation().getBlockZ()) - Math.abs(data.USP_Z)) > 2) {
            if (data.deltaY >= 0 || data.deltaY == 0) {
                if(1000 > System.currentTimeMillis() - data.lastFlag){
                    flag(player);
                    if(IRP_Setback){
                        if(250 > System.currentTimeMillis() - data.lastFlag) {
                            player.teleport(new Location(event.getPlayer().getWorld(), data.SetBackX, data.SetBackY, data.SetBackZ, data.USP_YAW, data.USP_PITCH));
                        }
                    }
                }
                data.lastFlag = System.currentTimeMillis();
            } else {
                //Falling
            }
        }
    }
}