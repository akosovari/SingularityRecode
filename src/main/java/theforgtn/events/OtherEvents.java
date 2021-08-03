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

        data.IRPB_X = abs(event.getPlayer().getLocation().getBlockX());
        data.IRPB_Y = abs(event.getPlayer().getLocation().getBlockY());
        data.IRPB_Z = abs(event.getPlayer().getLocation().getBlockZ());

        data.GSP_X = event.getPlayer().getLocation().getX();
        data.GSP_Z = event.getPlayer().getLocation().getZ();
        data.GSP_Y= event.getPlayer().getLocation().getY();

    }

    @EventHandler
    public void respawn(PlayerRespawnEvent event) {
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(event.getPlayer().getPlayer());

        data.IRPB_X = abs(event.getPlayer().getLocation().getBlockX());
        data.IRPB_Y = abs(event.getPlayer().getLocation().getBlockY());
        data.IRPB_Z = abs(event.getPlayer().getLocation().getBlockZ());


        data.GSP_X = event.getPlayer().getLocation().getX();
        data.GSP_Z = event.getPlayer().getLocation().getZ();
        data.GSP_Y= event.getPlayer().getLocation().getY();

    }
}