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



    public IrregularPositions(String name, boolean enabled, boolean punishable, int max) {
        super(name, enabled, punishable, max);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {

        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(event.getPlayer());

        if (!ConfigFile.IrregularPositions_enabled && data.inCreative) {
            return;
        }

        if (abs(abs(event.getPlayer().getLocation().getBlockX()) - Math.abs(data.USP_X)) > 2 || abs(abs(event.getPlayer().getLocation().getBlockY()) - Math.abs(data.USP_Y)) > data.IRP_tolerance || abs(abs(event.getPlayer().getLocation().getBlockZ()) - Math.abs(data.USP_Z)) > 2) {

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