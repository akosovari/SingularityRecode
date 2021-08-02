package theforgtn.checks.movement;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.Actions;
import theforgtn.data.ConfigFile;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class IrregularPositionsA extends Actions {


    private double YTolerance = 2;

    public IrregularPositionsA(String name, boolean enabled, boolean punishable, int max) {
        super(name, enabled, punishable, max);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        Bukkit.broadcastMessage("IRPB elindúlt");
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(event.getPlayer());
        Material m = event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType();
        float deltaY = (float) (event.getTo().getY() - event.getFrom().getY());

        if (!ConfigFile.IrregularPositions_enabled) {
            return;
        }


        if (m == Material.VINE || m == Material.LADDER  || m == Material.SLIME_BLOCK || m == Material.WATER || data.velXTicks > 5 || data.airTicks < 2) {

            data.IRPB_X = abs(event.getPlayer().getLocation().getBlockX());
            data.IRPB_Y = abs(event.getPlayer().getLocation().getBlockY());
            data.IRPB_Z = abs(event.getPlayer().getLocation().getBlockZ());

        }

        if (data.ground) {
            YTolerance = 1.3;
        }
        if (m == Material.WATER && !data.ground) {
            YTolerance = 0.5;
        }

        if (abs(abs(event.getPlayer().getLocation().getBlockX()) - data.IRPB_X) > 2 || abs(abs(event.getPlayer().getLocation().getBlockY()) - data.IRPB_Y) > YTolerance || abs(abs(event.getPlayer().getLocation().getBlockZ()) - data.IRPB_Z) > 2) {
            if (deltaY >= 0) {

                flag(event.getPlayer());

                if (ConfigFile.cancel_enabled) {

                    float pitch = event.getPlayer().getLocation().getPitch();
                    float yaw = event.getPlayer().getLocation().getYaw();
                    event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), data.IRPB_X, data.IRPB_Y, data.IRPB_Z, yaw, pitch));

                }

            } else {
                //Zuhanás, eltelt idő kiszámolása, a minecraftbeli gravitációs gyorsulással ==> ha nem egyenlő akkor flag
                return;
            }

        }


    }
}