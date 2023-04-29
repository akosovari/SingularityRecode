package theforgtn.checks.movement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;
import theforgtn.Actions;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class GroundSpeed extends Actions {
    public GroundSpeed(String name, boolean enabled, int max) { super(name, enabled, max); }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        // I know how it looks like but trust me, this check is hardly never falses.
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if(!enabled || !Main.getInstance().enabled || data.deltaY > 0.49|| !data.clientGround  || data.IcePosition || data.withElytra || data.usingRiptide || event.getPlayer().isInsideVehicle() || event.getPlayer().getAllowFlight() || abs(data.velXTicks) > 1 || abs(data.velZTicks) > 2 || data.SlimePosition){ return; }
        // Ground
        //Bukkit.broadcastMessage("DeltaXZ: "+ (data.deltaXZ) +"  "+ "Match: "+ (data.deltaY - data.Ylast_data));
        if(data.clientGround) {
            if (player.hasPotionEffect(PotionEffectType.SPEED) || data.blockAbove) {
                if(data.deltaXZ > 0.9) {
                    if(System.currentTimeMillis() - data.ground_speed_buffer < 100) {
                        flag(player, 0);
                        SetBack(player, 0);
                    }
                    data.ground_speed_buffer = System.currentTimeMillis();
                }

            } else {
                if(data.deltaXZ > 0.40) {
                    if (System.currentTimeMillis() - data.ground_speed_buffer < 100) {
                        flag(player, 0);
                        SetBack(player, 0);
                    }
                    data.ground_speed_buffer = System.currentTimeMillis();
                }
            }
        }
        // SusData
        if(Math.abs(data.deltaY - data.Ylast_data) > 0.3 && data.clientGround && data.groundTicks > 20) {
            if (System.currentTimeMillis() - data.ground_speed_buffer < 100) {
                flag(player, 0);
                SetBack(player, 0);
            }
            data.ground_speed_buffer = System.currentTimeMillis();
        }
        data.Ylast_data = data.deltaY;
    }
}