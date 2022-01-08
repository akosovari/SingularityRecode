package theforgtn.checks.packet;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.ReactWith;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class Timer extends ReactWith {
    public Timer(String name, boolean enabled, int max) { super(name, enabled, max); }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        // Well it's not a packet based timer check, but it gets the job done. At least I hope :'D
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if(!enabled || !Main.getInstance().enabled ) { return; }
        if(data.mpps > 25 && System.currentTimeMillis() - data.lastTeleport > 1000){
            flag(player,0);
            SetBack(player,1);
        }
    }
}