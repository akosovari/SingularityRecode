package theforgtn.checks.packet;

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

public class BadPacketsA extends Actions {



    public BadPacketsA(String name, boolean enabled, boolean punishable, int max) {
        super(name, enabled, punishable, max);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMove(PlayerMoveEvent event) {

        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(event.getPlayer());

            if(Math.abs(event.getPlayer().getLocation().getPitch()) > 90)

                flag(event.getPlayer());
                if(ConfigFile.BPA_Setback){
                    event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), data.USP_X, data.USP_Y, data.USP_Z, data.USP_YAW, data.USP_PITCH));
                }
    }
}