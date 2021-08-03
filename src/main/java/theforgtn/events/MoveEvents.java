package theforgtn.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import theforgtn.Main;
import theforgtn.data.PlayerData;

public class MoveEvents implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onMove(PlayerMoveEvent event) {

        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);

        data.ground = event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType().isSolid();
        data.deltaY = event.getTo().getY() - event.getFrom().getY();
        data.ping = event.getPlayer().getPing();
        data.clientGround = event.getPlayer().isOnGround();

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

