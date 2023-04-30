package theforgtn.checks.movement;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.Actions;
import theforgtn.Main;
import theforgtn.data.ConfigFile;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class PredictiveFly extends Actions {
    public PredictiveFly(String name, boolean enabled, int max) {
        super(name, enabled, max);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if (!enabled || !Main.getInstance().enabled || player.isInsideVehicle() || data.levitation || data.isInWater || data.standingInBlock || data.vanillaGround || (data.ground && data.clientGround) || data.withElytra || data.usingRiptide || data.onBoat || data.blockAbove || data.inCreative || player.getPlayer().getAllowFlight() || data.SlimePosition || abs(data.velXTicks) > 2 || abs(data.velYTicks) > 2 || abs(data.velZTicks) > 2) {
            return;
        }
        try {
            data.FlyDistY = event.getTo().getY() - event.getFrom().getY();
            data.FlypredictedDist = (data.FlylastDistY - 0.08D) * 0.9800000190734863D;
            if (!data.NearGround && !data.FlyLastOnGround && !data.FlyLastLastOnGround && Math.abs(data.FlyDistY) > 0.17) {
                if (!isRoughlyEqual(data.FlyDistY, data.FlypredictedDist)) {
                    if (Math.abs(data.FlyDistY) - data.FlypredictedDist > -0.20) {
                        if (System.currentTimeMillis() - data.predFlybuffer < 100) {
                            //Bukkit.broadcastMessage(data.FlyDistY + "    " + data.FlypredictedDist);
                            flag(player, 0);
                            SetBack(player, 0);
                        }
                        data.predFlybuffer = System.currentTimeMillis();
                    }
                }
            }
            data.FlyLastLastOnGround = data.FlyLastOnGround;
            data.FlyLastOnGround = data.NearGround;
            data.FlylastDistY = data.FlyDistY;
        } catch (Exception e){
            if(ConfigFile.debug){
                Main.getInstance().getLogger().warning("| Generated an exception [" + e.getCause() + "]");
            }
        }
    }
    public boolean isRoughlyEqual(double d1, double d2){
        return Math.abs(d1 - d2) < 0.01;
    }
}