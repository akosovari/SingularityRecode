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

public class IrregularPositions extends Actions {


    private double YTolerance = 2;

    public IrregularPositions(String name, boolean enabled, boolean punishable, int max) {
        super(name, enabled, punishable, max);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {

        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(event.getPlayer());
        Material m = event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType();

        if (!ConfigFile.IrregularPositions_enabled) {
            return;
        }

        if (data.ground) {
            YTolerance = 1.3;
        }
        if (m == Material.WATER && !data.ground) {
            YTolerance = 0.5;
        }

        if (abs(abs(event.getPlayer().getLocation().getBlockX()) - data.USP_X) > 2 || abs(abs(event.getPlayer().getLocation().getBlockY()) - data.USP_Y) > YTolerance || abs(abs(event.getPlayer().getLocation().getBlockZ()) - data.USP_Z) > 2) {

            if (data.deltaY >= 0) {

                flag(event.getPlayer());

                if (ConfigFile.cancel_enabled) {

                    event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), data.USP_X, data.USP_Y, data.USP_Z, data.USP_YAW, data.USP_PITCH));

                }

            } else {
                //Zuhanás, eltelt idő kiszámolása, a minecraftbeli gravitációs gyorsulással ==> ha nem egyenlő akkor flag
                return;
            }

        }


    }
}