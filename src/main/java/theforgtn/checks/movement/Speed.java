package theforgtn.checks.movement;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.Actions;
import theforgtn.Main;
import theforgtn.data.ConfigFile;
import theforgtn.data.PlayerData;

public class Speed extends Actions {
    public Speed(String name,  boolean enabled, boolean punishable, int max) {
        super(name, enabled, punishable, max);
    }
    private double lastDist;
    float friction = 0.92F;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event) {

        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(event.getPlayer());

        if(event.getPlayer().isGliding() || event.getPlayer().isInsideVehicle() || event.getPlayer().getAllowFlight()){ return; }

        data.speed_distX = event.getTo().getX() - event.getFrom().getX();
        data.speed_distZ = event.getTo().getZ() - event.getFrom().getZ();
        data.speed_dist = (data.speed_distX * data.speed_distX) + (data.speed_distZ * data.speed_distZ);

        data.speed_lastDist = this.lastDist;
        this.lastDist = data.speed_dist;



        data.speed_shiftedLastDist = lastDist * friction;
        data.speed_equalness = data.speed_dist - data.speed_shiftedLastDist;
        data.speed_scaledEqualness = data.speed_equalness * 138;

        if(!data.clientGround && !data.speed_lastOnGround && !data.isInWater) {
            if(data.speed_scaledEqualness > 1.5) {
               if(2000 > System.currentTimeMillis() - data.lastFlag) {
                   flag(event.getPlayer());
                   if (ConfigFile.SpeedA_Setback) {
                       event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), data.USP_X, data.USP_Y, data.USP_Z, data.USP_YAW, data.USP_PITCH));

                   }
               }
               data.lastFlag = System.currentTimeMillis();
           }
        }


        data.speed_lastOnGround = data.clientGround;

    }
}

