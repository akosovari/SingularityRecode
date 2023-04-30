package theforgtn.checks.movement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import theforgtn.Actions;
import theforgtn.Main;
import theforgtn.data.ConfigFile;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class SwimGlitch extends Actions {
    public SwimGlitch(String name, boolean enabled, int max) { super(name, enabled, max); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        if (!enabled || !Main.getInstance().enabled) {
            return;
        }
        try {
            org.bukkit.entity.Player player = event.getPlayer();
            PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
            player.getScheduler().runAtFixedRate(Main.getInstance(), scheduledTask -> {

                if ((player.isSwimming() && !data.last_swim) || (!player.isSwimming() && data.last_swim)) {
                    data.swim_switch_a_sec++;
                    if (data.swim_switch_a_sec > 10) {
                        flag(player, 0);
                        SetBack(player, 0);
                    }
                }
                data.last_swim = player.isSwimming();
            }, null, 3L, 1L);

            player.getScheduler().runAtFixedRate(Main.getInstance(), scheduledTask -> {
                data.swim_switch_a_sec = 0;
            }, null, 1L, 20L);
        }catch (Exception e){
            if(ConfigFile.debug){
                Main.getInstance().getLogger().warning("| Generated an exception [" + e.getCause() + "]");
            }
        }
    }
}