package theforgtn.checks.movement;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.ReactWith;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class IrregularPositions extends ReactWith {
    public IrregularPositions(String name, boolean enabled, int max) { super(name, enabled, max); }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        // My first check, maybe I should optimalise it in the future.
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        Material m = player.getLocation().subtract(0, 1, 0).getBlock().getType();
        if (!enabled || !Main.getInstance().enabled || data.withElytra || data.inCreative || player.getAllowFlight() || player.isInsideVehicle() || data.usingRiptide) { return; }
        if (data.ground) { data.IRP_tolerance = 1.25; }
        if (m == Material.WATER && !data.clientGround) { data.IRP_tolerance = 1.5; }
        if(data.jumpBoost){ data.IRP_tolerance = 2.5; }
        if (abs(abs(player.getLocation().getBlockX()) - Math.abs(data.USP_X)) > 2 || abs(abs(player.getLocation().getBlockY()) - Math.abs(data.USP_Y)) > data.IRP_tolerance || abs(abs(player.getLocation().getBlockZ()) - Math.abs(data.USP_Z)) > 2) {
            if (data.deltaY >= 0 || data.deltaY == 0) {
                    flag(player,0);
                    SetBack(player,3);
            } else {
                //Falling
            }
        }
    }
}