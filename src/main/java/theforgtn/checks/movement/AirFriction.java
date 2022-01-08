package theforgtn.checks.movement;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;
import theforgtn.ReactWith;
import theforgtn.Main;
import theforgtn.data.ConfigFile;
import theforgtn.data.PlayerData;
import static java.lang.Math.abs;

public class AirFriction extends ReactWith {
    public AirFriction(String name, boolean enabled, int max) { super(name, enabled, max); }
    private double lastDist;
    float friction = 0.98F;
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        // Thanks for Jonhan, look up him on youtube, he is amaising. :)
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if(!enabled || !Main.getInstance().enabled || data.IcePosition || player.hasPotionEffect(PotionEffectType.SPEED) || player.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE) || data.withElytra || data.usingRiptide || player.isInsideVehicle() || player.getAllowFlight() || abs(data.velXTicks) > 2 || abs(data.velYTicks) > 2 || abs(data.velZTicks) > 2 || data.SlimePosition){ return; }
        data.speed_distX = event.getTo().getX() - event.getFrom().getX();
        data.speed_distZ = event.getTo().getZ() - event.getFrom().getZ();
        data.speed_dist = (data.speed_distX * data.speed_distX) + (data.speed_distZ * data.speed_distZ);
        data.speed_lastDist = this.lastDist;
        this.lastDist = data.speed_dist;
        data.speed_shiftedLastDist = lastDist * friction;
        data.speed_equalness = data.speed_dist - data.speed_shiftedLastDist;
        data.speed_scaledEqualness = data.speed_equalness * 138;
        if(!data.clientGround && !data.speed_lastOnGround && !data.isInWater) {
            if(data.speed_scaledEqualness > 0.5) {
                   flag(player,0);
                   SetBack(player, 0);
           }
        }
        data.speed_lastOnGround = data.clientGround;
    }
}

