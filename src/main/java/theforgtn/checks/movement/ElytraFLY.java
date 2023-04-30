package theforgtn.checks.movement;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.Actions;
import theforgtn.Main;
import theforgtn.data.ConfigFile;
import theforgtn.data.PlayerData;

public class ElytraFLY extends Actions {
    public ElytraFLY(String name, boolean enabled, int max) { super(name, enabled, max); }
    // This check is from my AntiElytraLagg plugin. https://www.spigotmc.org/resources/antielytralag-newchunks-anticheat-for-elytra.95803/
    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerMoveEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        try {
            if (!enabled || !Main.getInstance().enabled) {
                return;
            }
            if (player.isGliding() && !(ConfigFile.anarchy_mode_enabled && data.speed < ConfigFile.elytrafly)) {
                // AntiCheat Section
                // SetBack
                if (data.elyLastdeltaXZ < 1.7F && !((event.getTo().getY() - event.getFrom().getY()) == data.elyLastDeltaY && player.getLocation().getPitch() != 90)) {
                    data.ely_setback_loc = player.getLocation();
                }
                // XZ
                if (1.7F < data.elyLastdeltaXZ && data.elyLastdeltaXZ == ((double) Math.sqrt(Math.pow(event.getTo().getX() - event.getFrom().getX(), 2) + Math.pow(event.getTo().getZ() - event.getFrom().getZ(), 2)))) {
                    flag(player, 0);
                    SetBack(player, 4);
                    // player.teleportAsync(new Location(event.getPlayer().getWorld(), data.elyX, data.elyY, data.elyZ, player.getLocation().getYaw(), player.getLocation().getPitch()));
                }
                // Y
                if ((event.getTo().getY() - event.getFrom().getY()) == data.elyLastDeltaY && player.getLocation().getPitch() != 90) {
                    flag(player, 0);
                    SetBack(player, 4);
                    // player.teleportAsync(new Location(event.getPlayer().getWorld(), data.elyX, data.elyY, data.elyZ, player.getLocation().getYaw(), player.getLocation().getPitch()));
                }

                // Last tick data
                data.elyLastdeltaXZ = Math.sqrt(Math.pow(event.getTo().getX() - event.getFrom().getX(), 2) + Math.pow(event.getTo().getZ() - event.getFrom().getZ(), 2));
                data.elyLastDeltaY = event.getTo().getY() - event.getFrom().getY();
            }
        } catch (Exception e){
            if(ConfigFile.debug){
                Main.getInstance().getLogger().warning("| Generated an exception [" + e.getCause() + "]");
            }
        }
    }
}