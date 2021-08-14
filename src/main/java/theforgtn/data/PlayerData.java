package theforgtn.data;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import theforgtn.Main;

public class PlayerData {
    public org.bukkit.entity.Player player;
    public LivingEntity lastHitEntity;

    //Universal values
    public boolean ground, clientGround, inCreative, onBoat, isInWater, jumpBoost, levitation;
    public int violations, airTicks, groundTicks, ping;
    public double deltaY;
    public float USP_X, USP_Y, USP_Z, USP_YAW, USP_PITCH, deltaXZ, lastFlag;
    //Velocity
    public float lastVelocityTaken;
    public int velXTicks;

    //IRP
    public double IRP_tolerance;
    //GSP
    public double GSP_damage;
    public int GSP_Buffer;

    //Speed
    public double speed_distX ,speed_distZ, speed_dist, speed_lastDist, speed_shiftedLastDist, speed_equalness, speed_scaledEqualness;
    public boolean speed_lastOnGround;

    //BlockInteractions
    public float lastPlacedBlock_X, lastPlacedBlock_Y, lastPlacedBlock_Z;

    public PlayerData(org.bukkit.entity.Player player) {
        this.player = player;
        new BukkitRunnable() {
            public void run() {

            }
        }.runTaskTimer(Main.getInstance(), 0L, 1L);
    }

}
