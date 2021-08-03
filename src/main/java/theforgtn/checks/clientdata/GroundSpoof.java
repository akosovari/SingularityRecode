package theforgtn.checks.clientdata;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.Actions;
import theforgtn.Main;
import theforgtn.data.ConfigFile;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;
import static theforgtn.data.ConfigFile.cancel_enabled;
import static theforgtn.data.ConfigFile.cancel_vl;

public class GroundSpoof extends Actions {

    public GroundSpoof(String name, boolean enabled, boolean punishable, int max) {
        super(name, enabled, punishable, max);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onMove(PlayerMoveEvent event) {
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(event.getPlayer());
        Material m = event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType();


        if (m == Material.VINE || m == Material.LADDER  || m == Material.SLIME_BLOCK || m == Material.WATER || data.airTicks < 2) {

            data.GSP_X = event.getPlayer().getLocation().getX();
            data.GSP_Z = event.getPlayer().getLocation().getZ();
            data.GSP_Y= event.getPlayer().getLocation().getY();

        }
        if (abs(abs(event.getPlayer().getLocation().getBlockX()) - data.IRPB_X) > 1 || abs(abs(event.getPlayer().getLocation().getBlockY()) - data.IRPB_Y) > 1 || abs(abs(event.getPlayer().getLocation().getBlockZ()) - data.IRPB_Z) > 1) {

            if (data.ground != data.clientGround && data.deltaY < 0.01) {

                flag(event.getPlayer());

                if (data.GSP_damage < ((event.getPlayer().getLocation().getY() - event.getPlayer().getWorld().getHighestBlockYAt(event.getPlayer().getLocation())) - 3)) {

                    data.GSP_damage = ((event.getPlayer().getLocation().getY() - event.getPlayer().getWorld().getHighestBlockYAt(event.getPlayer().getLocation())) - 3);

                }

                if (ConfigFile.GSP_setback) {

                        float pitch = event.getPlayer().getLocation().getPitch();
                        float yaw = event.getPlayer().getLocation().getYaw();
                        event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), data.GSP_X, data.GSP_Y, data.GSP_Z, yaw, pitch));

                }
                if(ConfigFile.GSP_damage){
                    event.getPlayer().damage(data.GSP_damage);
                }
            }
        }

    }
}

