package theforgtn.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import theforgtn.Main;
import theforgtn.data.PlayerData;

public class OtherEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Main.getInstance().getDataManager().add(e.getPlayer());
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(e.getPlayer().getPlayer());


    }

    @EventHandler
    public void respawn(PlayerRespawnEvent e) {
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(e.getPlayer().getPlayer());

    }
}