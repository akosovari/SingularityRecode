package theforgtn.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.potion.PotionEffectType;
import theforgtn.Main;
import theforgtn.data.PlayerData;

public class MoveEvents implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
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
        data.isInWater = event.getPlayer().isInWater();
        data.inCreative = event.getPlayer().getGameMode().equals(GameMode.CREATIVE);
        data.jumpBoost = event.getPlayer().hasPotionEffect(PotionEffectType.JUMP);
        data.levitation = event.getPlayer().hasPotionEffect(PotionEffectType.LEVITATION);
        //Universal SetBack Positions
        if (m == Material.VINE || m == Material.LADDER  || m == Material.SLIME_BLOCK || m == Material.TWISTING_VINES || m == Material.WATER || m == Material.LILY_PAD || data.velXTicks > 2 || data.airTicks < 2 || data.levitation || data.ground || data.isInWater || data.airTicks > 20) {
                data.USP_X = event.getPlayer().getLocation().getBlockX();
                data.USP_Y = event.getPlayer().getLocation().getBlockY();
                data.USP_Z = event.getPlayer().getLocation().getBlockZ();
        }
        //IRP_Tolerance
        if (data.ground) {
            data.IRP_tolerance = 1.25;
        }
        if (m == Material.WATER && !data.clientGround) {
            data.IRP_tolerance = 1.5;
        }
        if(data.jumpBoost){
            data.IRP_tolerance = 2.5;
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

