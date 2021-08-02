package theforgtn.data;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import theforgtn.Main;

public class PlayerData {
    public org.bukkit.entity.Player player;
    public LivingEntity lastHitEntity;

    //Universal values
    public boolean ground;
    public int violations, airTicks, groundTicks;

     //Velocity
    public float lastVelocityTaken;
    public int velXTicks;

    //IRP
    public float IRPB_X;
    public float IRPB_Y;
    public float IRPB_Z;

    public PlayerData(org.bukkit.entity.Player player) {
        this.player = player;



        new BukkitRunnable() {
            public void run() {

            }
        }.runTaskTimer(Main.getInstance(), 0L, 1L);
    }

}
