package theforgtn.checks.movement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import theforgtn.Actions;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class LocDiffPerTick extends Actions {
    public LocDiffPerTick(String name, boolean enabled, int max) { super(name, enabled, max); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        player.getScheduler().runAtFixedRate(Main.getInstance(), scheduledTask -> {
            if ((200) < player.getLocation().getChunk().getInhabitedTime()){
                data.last_full_chunk = player.getLocation();
            } else {
                if(data.last_full_chunk != null && data.last_full_chunk.getWorld() == player.getWorld()) {
                    flag(player,4);
                    SetBack(player, 4);
                }
            }
        }, null, 3L, 1L);
    }
}