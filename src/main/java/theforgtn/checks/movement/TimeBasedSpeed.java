package theforgtn.checks.movement;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;
import theforgtn.ReactWith;
import theforgtn.data.ConfigFile;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class TimeBasedSpeed extends ReactWith {
    public TimeBasedSpeed(String name, boolean enabled, int max) { super(name, enabled, max); }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        // Well it's not a packet based timer check, but it gets the job done. At least I hope :'D
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if(!enabled || !Main.getInstance().enabled) { return; }
        if(data.suspectedByTimeCheck){
            if((data.TBSpeedXZ > 50 && !data.teleportedByPlugin)|| player.isInsideVehicle()){
                data.TBSpeedX = (float) player.getLocation().getX();
                data.TBSpeedZ= (float) player.getLocation().getZ();
                return;
            }
            if(System.currentTimeMillis() - data.lastTeleport > data.TBSFreq || !data.teleportedByPlugin){
                flag(player,0);
                SetBack(player,3);
            }
            data.suspectedByTimeCheck = false;
        }
        //        data.TBSFreq = 250 + data.ping + Main.getInstance().tickTime +  ConfigFile.TimeBasedSpeed_freq_offset;
        data.TBSFreq = 250 + data.ping + Main.getInstance().tickTime;
        data.TBSBorder = Math.pow(data.TBSFreq/60, 1.2);
        if(data.TBSpeedXZ > data.TBSBorder) {
           data.suspectedByTimeCheck = true;
       }
        // LastEvent Data
        if((System.currentTimeMillis() - data.TimeBasedSpeed  > data.TBSFreq) || (data.IcePosition || player.hasPotionEffect(PotionEffectType.SPEED) || player.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE) || data.withElytra || data.usingRiptide || player.isInsideVehicle() || player.getAllowFlight() || abs(data.velXTicks) > 2 || abs(data.velYTicks) > 2 || abs(data.velZTicks) > 2 || data.SlimePosition)) {
            data.TBSpeedX = (float) player.getLocation().getX();
            data.TBSpeedZ= (float) player.getLocation().getZ();
            data.TimeBasedSpeed = System.currentTimeMillis();
        }
    }
}