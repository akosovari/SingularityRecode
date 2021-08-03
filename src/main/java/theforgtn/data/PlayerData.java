package theforgtn.data;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import theforgtn.Main;

public class PlayerData {
    public org.bukkit.entity.Player player;
    public LivingEntity lastHitEntity;

    //Universal values
    public boolean ground, clientGround;
    public int violations, airTicks, groundTicks, ping;
    public double deltaY;
    //Universal setback position
    public float USP_X, USP_Y, USP_Z;

    //Velocity
    public float lastVelocityTaken;
    public int velXTicks;

    //IRP
    public float IRPB_X, IRPB_Y, IRPB_Z;
    //GSP
    public double GSP_X, GSP_Y, GSP_Z, GSP_damage;
    public int GSP_Buffer;


    public PlayerData(org.bukkit.entity.Player player) {
        this.player = player;
        new BukkitRunnable() {
            public void run() {

            }
        }.runTaskTimer(Main.getInstance(), 0L, 1L);
    }

}
