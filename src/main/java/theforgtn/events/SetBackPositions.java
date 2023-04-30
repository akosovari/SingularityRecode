package theforgtn.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import java.util.HashMap;

public class SetBackPositions implements Listener {
    HashMap<String, Location> locations = new HashMap<String, Location>();
    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);

        player.getScheduler().runAtFixedRate(Main.getInstance(),scheduledTask -> {

        }, null, 10L, 10L);
    }
}
