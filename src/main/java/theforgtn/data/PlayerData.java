package theforgtn.data;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import theforgtn.Main;

public class PlayerData {
    public org.bukkit.entity.Player player;
    //Hey! I know it seems frustrating, but I can assure you, it IS frustrating. :D
    public boolean ground, vanillaGround, NearGround, clientGround, inCreative, onBoat, isInWater, jumpBoost, levitation, SlimePosition, IcePosition, FlyLastOnGround, FlyLastLastOnGround, blockAbove, withElytra, standingInBlock, usingRiptide, isLevitating, suspectedByTimeCheck, teleportedByPlugin, GSP_damageGiven, speed_lastOnGround;
    public int violations, airTicks, groundTicks, ping, velXTicks, velYTicks, velZTicks, mppsEventFired, mpps;
    public double deltaY, lastFlag, FlyDistY, FlylastDistY, FlypredictedDist, lastElytra, lastSetBackPosReset, TimeBasedSpeed, lastVelocityTaken, lastTeleport, lastOnGround, IRP_tolerance, GSP_damage, VTMlast_deltaY, speed_distX, speed_distZ, speed_dist, speed_lastDist, speed_shiftedLastDist, speed_equalness, speed_scaledEqualness, lastOnFeet, TBSFreq, TBSBorder, elyChunkX, elyChunkY, elyChunkZ, elyX, elyY, elyZ, elyLastdeltaXZ, elyLastDeltaY ,mppsLastEventCount, mppslastPacket, mppsLastReset;
    public float USP_X, USP_Y, USP_Z, USP_YAW,  USP_PITCH, SetBackX, SetBackY, SetBackZ, groundX, groundZ, groundY, deltaXZ, SlimeX, SlimeZ, IceX, IceZ,BoatX, BoatY, BoatZ, BlockAboveX, BlockAboveZ, vl_reset_time, TBSpeedX, TBSpeedZ, TBSpeedXZ, HMLastXZ;

    //Constructor
    public PlayerData(org.bukkit.entity.Player player) {
        this.player = player;
        new BukkitRunnable() {
            public void run() {}
        }.runTaskTimer(Main.getInstance(), 0L, 1L);
    }
}
