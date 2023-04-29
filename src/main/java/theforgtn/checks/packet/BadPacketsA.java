package theforgtn.checks.packet;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.Actions;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class BadPacketsA extends Actions {



    public BadPacketsA(String name, boolean enabled, int max) {
        super(name, enabled, max);
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if(!enabled || !Main.getInstance().enabled){ return;}

        if(Math.abs(player.getLocation().getPitch()) > 90){
            flag(player,0);
            SetBack(player,1);
            }
        if(Math.abs(player.getLocation().getPitch()) < -90){
            flag(player,0);
            SetBack(player,1);
        }
    }
}