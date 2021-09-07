package theforgtn.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class OtherEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Main.getInstance().getDataManager().add(event.getPlayer());
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(event.getPlayer().getPlayer());

        data.USP_X = event.getPlayer().getLocation().getBlockX();
        data.USP_Y = event.getPlayer().getLocation().getBlockY();
        data.USP_Z = event.getPlayer().getLocation().getBlockZ();
        data.VTMlast_deltaY = data.deltaY;
    }

    @EventHandler
    public void respawn(PlayerRespawnEvent event) {
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(event.getPlayer().getPlayer());

        data.USP_X = event.getPlayer().getLocation().getBlockX();
        data.USP_Y = event.getPlayer().getLocation().getBlockY();
        data.USP_Z = event.getPlayer().getLocation().getBlockZ();
        data.VTMlast_deltaY = data.deltaY;
    }

}