package theforgtn.checks.movement;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import theforgtn.Actions;
import theforgtn.data.ConfigFile;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;
import static java.lang.Math.exp;

public class Fly extends Actions {


    private double lastDistY;
    private boolean lastOnGround;
    private boolean lastLastOnGround;

    public Fly(String name, boolean enabled, boolean punishable, int max) {
        super(name, enabled, punishable, max);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {

        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(event.getPlayer());
        Vector v = event.getPlayer().getVelocity();

        if (event.getPlayer().isInsideVehicle()) {
            return;
        }
        double distY = event.getTo().getY() - event.getFrom().getY();
        double lastDistY = this.lastDistY;
        this.lastDistY = distY;

        double predictedDist = (lastDistY - 0.08D) * 0.9800000190734863D;

        boolean onGround = isNearGround(event.getTo());

        boolean lastOnGround = this.lastOnGround;
        this.lastOnGround = onGround;

        boolean lastLastOnGround = this.lastLastOnGround;
        this.lastLastOnGround = lastOnGround;

        if(!onGround && !lastOnGround && lastLastOnGround && Math.abs(predictedDist) >= 0.005D){
            if(!isRoughlyEqual(distY,predictedDist)){
                if(3000 > System.currentTimeMillis() - data.lastFlag) {
                    flag(event.getPlayer());
                    if (ConfigFile.FLY_Setback)
                        v.setY(-10.0);
                        event.getPlayer().setVelocity(v);
                        if(1000 > System.currentTimeMillis() - data.lastFlag) {
                            event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), data.USP_X, data.USP_Y, data.USP_Z, data.USP_YAW, data.USP_PITCH));
                            if(data.ground) {
                                event.getPlayer().damage(data.GSP_damage);
                            }
                        }
                    }
                }
                data.lastFlag = System.currentTimeMillis();
            }
        }

    public boolean isRoughlyEqual(double d1, double d2){
        return Math.abs(d1 - d2) < 0.001;
    }

    public boolean isNearGround(Location location){
        double expand = 0.3;
        for (double x = -expand; x <= expand; x += expand){
            for (double z = -expand; z <= expand; z += expand){
                if (location.clone().add(x, -0.5001, z).getBlock().getType() != Material.AIR){
                    return true;
                }
            }
        }
        return false;
    }
}