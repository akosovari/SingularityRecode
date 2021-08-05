package theforgtn.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class MoveEvents implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onMove(PlayerMoveEvent event) {

        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        Material m = event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType();

        //Universal values
        data.ground = event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType().isSolid();
        data.deltaY = event.getTo().getY() - event.getFrom().getY();
        data.ping = event.getPlayer().getPing();
        data.clientGround = event.getPlayer().isOnGround();
        data.USP_PITCH = event.getPlayer().getLocation().getPitch();
        data.USP_YAW = event.getPlayer().getLocation().getYaw();
        data.deltaXZ = (float) Math.sqrt(Math.pow(event.getTo().getX() - event.getFrom().getX(), 2) + Math.pow(event.getTo().getZ() - event.getFrom().getZ(), 2));

        //Universal SetBack Position
        if (m == Material.VINE || m == Material.LADDER  || m == Material.SLIME_BLOCK || m == Material.TWISTING_VINES || m == Material.WATER || data.velXTicks > 5 || data.airTicks < 2) {

            data.USP_X = event.getPlayer().getLocation().getBlockX();
            data.USP_Y = event.getPlayer().getLocation().getBlockY();
            data.USP_Z = event.getPlayer().getLocation().getBlockZ();

        }
        //IRP_Tolerance
        if (data.ground) {
            data.IRP_tolerance = 1.3;
        }
        if (m == Material.WATER && !data.ground) {
            data.IRP_tolerance = 0.5;
        }

        //Tick counting
        if ((event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType().isSolid()
                || event.getPlayer().getLocation().subtract(1, 1, 0).getBlock().getType().isSolid()
                || event.getPlayer().getLocation().subtract(0, 1, 1).getBlock().getType().isSolid()
                || event.getPlayer().getLocation().subtract(-1, 1, 0).getBlock().getType().isSolid()
                || event.getPlayer().getLocation().subtract(0, 1, -1).getBlock().getType().isSolid())) {
            if (!(event.getPlayer().getLocation().subtract(0, 2, 0).getBlock().getType() == Material.AIR)) {
                data.groundTicks++;
                data.airTicks = 0;
            } else {
                data.airTicks++;
                data.groundTicks = 0;
            }
        } else {
            data.airTicks++;
            data.groundTicks = 0;

        }
        if(player.getGameMode() == GameMode.CREATIVE) {
            data.inCreative = true;
        } else {
            data.inCreative = false;
        }
    }


    @EventHandler
    public void onVelocity(PlayerVelocityEvent event) {
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(event.getPlayer());

        if (data == null) {
            return;
        }
        if (event.getVelocity().getY() > -0.078 || event.getVelocity().getY() < -0.08) {
            data.lastVelocityTaken = System.currentTimeMillis();
        }

        data.velXTicks = (int) Math.round(event.getVelocity().getX() * 100);
        data.velXTicks = (int) Math.round(event.getVelocity().getY() * 100);
        data.velXTicks = (int) Math.round(event.getVelocity().getZ() * 100);

    }
}

