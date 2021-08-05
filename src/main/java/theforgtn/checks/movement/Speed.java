package theforgtn.checks.movement;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.Actions;
import theforgtn.Main;
import theforgtn.data.PlayerData;

public class Speed extends Actions {
    public Speed(String name,  boolean enabled, boolean punishable, int max) {
        super(name, enabled, punishable, max);
    }
    private double lastDist;
    private boolean lastOnGround;
    float friction = 0.91F;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerMove(PlayerMoveEvent event) {

        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(event.getPlayer());
        data.speed_distX = event.getTo().getX() - event.getFrom().getX();
        data.speed_distZ = event.getTo().getZ() - event.getFrom().getZ();
        data.speed_dist = (data.speed_distX * data.speed_distX) + (data.speed_distZ * data.speed_distZ);

        data.speed_lastDist = this.lastDist;
        this.lastDist = data.speed_dist;



        data.speed_shiftedLastDist = lastDist * friction;
        data.speed_equalness = data.speed_dist - data.speed_shiftedLastDist;
        data.speed_scaledEqualness = data.speed_equalness * 138;

        if(!data.clientGround && !data.speed_lastOnGround && data.speed_scaledEqualness > 1.5) {
            Bukkit.broadcastMessage("Value " + data.speed_scaledEqualness);
            flag(event.getPlayer());

        }


        if(data.clientGround){
            data.speed_lastOnGround = true;
        } else {
            data.speed_lastOnGround = false;
        }

    }
}

