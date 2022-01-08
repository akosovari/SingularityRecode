package theforgtn.checks.movement;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.ReactWith;
import theforgtn.Main;
import theforgtn.data.ConfigFile;
import theforgtn.data.PlayerData;

public class ElytraFLY extends ReactWith {
    public ElytraFLY(String name, boolean enabled, int max) { super(name, enabled, max); }
    // This check is from my AntiElytraLagg plugin. https://www.spigotmc.org/resources/antielytralag-newchunks-anticheat-for-elytra.95803/
    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerMoveEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if(!enabled || !Main.getInstance().enabled || (ConfigFile.anarchy_mode_enabled && !Main.getInstance().tps_protection)) { return; }
        if(player.isGliding()) {
            // Chunk load section
            if ((100) < player.getLocation().getChunk().getInhabitedTime()){
                data.elyChunkX = player.getLocation().getX();
                data.elyChunkY = player.getLocation().getY();
                data.elyChunkZ = player.getLocation().getZ();
            } else {
                player.teleport(new Location(event.getPlayer().getWorld(), data.elyChunkX, data.elyChunkY, data.elyChunkZ, player.getLocation().getYaw(), player.getLocation().getPitch()));
            }

            // AntiCheat Section
            // SetBack
            if(data.elyLastdeltaXZ < 1.7F && !((event.getTo().getY() - event.getFrom().getY()) == data.elyLastDeltaY && player.getLocation().getPitch() != 90)){
                data.elyX = player.getLocation().getX();
                data.elyY = player.getLocation().getY();
                data.elyZ = player.getLocation().getZ();
            }
            // XZ
            if (1.7F < data.elyLastdeltaXZ && data.elyLastdeltaXZ == ((double) Math.sqrt(Math.pow(event.getTo().getX() - event.getFrom().getX(), 2) + Math.pow(event.getTo().getZ() - event.getFrom().getZ(), 2)))) {
                flag(player,0);
                player.teleport(new Location(event.getPlayer().getWorld(), data.elyX, data.elyY, data.elyZ, player.getLocation().getYaw(), player.getLocation().getPitch()));
            }
            // Y
            if((event.getTo().getY() - event.getFrom().getY()) == data.elyLastDeltaY && player.getLocation().getPitch() != 90) {
                flag(player,0);
                player.teleport(new Location(event.getPlayer().getWorld(), data.elyX, data.elyY, data.elyZ, player.getLocation().getYaw(), player.getLocation().getPitch()));
            }
            // Last tick data
            data.elyLastdeltaXZ = Math.sqrt(Math.pow(event.getTo().getX() - event.getFrom().getX(), 2) + Math.pow(event.getTo().getZ() - event.getFrom().getZ(), 2));
            data.elyLastDeltaY = event.getTo().getY() - event.getFrom().getY();
        } else {
            data.elyChunkX = data.elyX = player.getLocation().getX();
            data.elyChunkY = data.elyY =player.getLocation().getY();
            data.elyChunkZ = data.elyZ = player.getLocation().getZ();
        }

    }
}