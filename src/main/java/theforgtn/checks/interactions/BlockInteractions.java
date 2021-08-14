package theforgtn.checks.interactions;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import theforgtn.Actions;


import static java.lang.Math.abs;

public class BlockInteractions extends Actions {



    public BlockInteractions(String name, boolean enabled, boolean punishable, int max) {
        super(name, enabled, punishable, max);
    }

    @EventHandler
    public void BlockPlace(BlockPlaceEvent event) {
        Bukkit.broadcastMessage("Distance " + abs(abs(event.getPlayer().getLocation().getBlockX()) - Math.abs(event.getBlock().getLocation().getBlockX())) + "  " + abs(abs(event.getPlayer().getLocation().getBlockY()) - Math.abs(event.getBlock().getLocation().getBlockY())) + "  " + abs(abs(event.getPlayer().getLocation().getBlockZ()) - Math.abs(event.getBlock().getLocation().getBlockZ())));
        if (abs(abs(event.getPlayer().getLocation().getBlockX()) - Math.abs(event.getBlock().getLocation().getBlockX())) > 4 || abs(abs(event.getPlayer().getLocation().getBlockY()) - Math.abs(event.getBlock().getLocation().getBlockY())) > 4 || abs(abs(event.getPlayer().getLocation().getBlockZ()) - Math.abs(event.getBlock().getLocation().getBlockZ())) > 4) {
            flag(event.getPlayer());
        }
    }
}
