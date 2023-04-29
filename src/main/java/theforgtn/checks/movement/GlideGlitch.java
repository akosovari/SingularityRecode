package theforgtn.checks.movement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import theforgtn.Actions;
import theforgtn.data.ConfigFile;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class GlideGlitch extends Actions {
    public GlideGlitch(String name, boolean enabled, int max) { super(name, enabled, max); }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        if(!enabled || !Main.getInstance().enabled) { return; }
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        player.getScheduler().runAtFixedRate(Main.getInstance(), scheduledTask -> {
            if(player.isGliding() && !(ConfigFile.anarchy_mode_enabled && data.speed < ConfigFile.elytrafly)) {
                if ((player.isGliding() && !data.last_glide) || (!player.isGliding() && data.last_glide)) {
                    data.glide_swithc_a_sec++;
                    if (data.glide_swithc_a_sec > 10) {
                        flag(player, 0);
                        SetBack(player, 4);
                    }
                }
                data.last_glide = player.isGliding();
            }
        }, null, 3L, 1L);

        player.getScheduler().runAtFixedRate(Main.getInstance(), scheduledTask -> {
            data.glide_swithc_a_sec = 0;
        }, null, 1L, 20L);
    }
}