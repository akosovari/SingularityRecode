package theforgtn.checks.movement;

import org.bukkit.*;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Strider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.ReactWith;
import theforgtn.data.ConfigFile;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class Vehicle extends ReactWith {
    public Vehicle(String name, boolean enabled, int max) {
        super(name, enabled, max);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if (!enabled || !Main.getInstance().enabled || !player.isInsideVehicle() || System.currentTimeMillis() - data.lastOnFeet < 1000 || data.IcePosition) {
            return;
        }
        // Anarchy mode
        if (ConfigFile.anarchy_mode_enabled) {
            if (Main.getInstance().tps_protection) {
                //Boat
                if (player.getVehicle() instanceof Boat) {
                    //Speed
                    if (data.deltaXZ > 2) {
                        flag(player, 0);
                        SetBack(player, 1);
                    }
                    //Fly
                    if (abs(abs(player.getLocation().getBlockX()) - Math.abs(data.USP_X)) > 5 || abs(abs(player.getLocation().getBlockY()) - Math.abs(data.USP_Y)) > 1.5 || abs(abs(player.getLocation().getBlockZ()) - Math.abs(data.USP_Z)) > 5) {
                        if (data.deltaY >= 0) {
                            flag(player, 0);
                            SetBack(player, 1);
                        }
                    }
                }
                //Horse
                if (player.getVehicle() instanceof Horse) {
                    if (data.deltaXZ > 1) {
                        flag(player, 0);
                        SetBack(player, 1);
                    }
                }
                //Pig
                if (player.getVehicle() instanceof Pig) {
                    if (data.deltaXZ > 1) {
                        flag(player, 0);
                        SetBack(player, 1);
                    }
                }
                //Strider
                if (player.getVehicle() instanceof Strider) {
                    if (data.deltaXZ > 1) {
                        flag(player, 0);
                        SetBack(player, 1);
                    }
                }
            }
        } else {
            //Boat
            if (player.getVehicle() instanceof Boat) {
                if (abs(abs(player.getLocation().getBlockX()) - Math.abs(data.USP_X)) > 5 || abs(abs(player.getLocation().getBlockY()) - Math.abs(data.USP_Y)) > 1.5 || abs(abs(player.getLocation().getBlockZ()) - Math.abs(data.USP_Z)) > 5) {
                    if (data.deltaY >= 0) {
                        flag(player, 0);
                        SetBack(player, 1);
                    }
                }
                if (data.IcePosition) {
                    if (data.deltaXZ > 0.4) {
                        flag(player, 0);
                        SetBack(player, 1);
                    } else {
                        if (data.deltaXZ > 0.8) {
                            flag(player, 0);
                            SetBack(player, 1);
                        }
                    }
                }
                //Horse
                if (player.getVehicle() instanceof Horse) {
                    if (data.deltaXZ > 1) {
                        flag(player, 0);
                        SetBack(player, 1);

                    }
                }
                //Pig
                if (player.getVehicle() instanceof Pig) {
                    if (data.deltaXZ > 1) {
                        flag(player, 0);
                        SetBack(player, 1);

                    }
                }
                //Strider
                if (player.getVehicle() instanceof Strider) {
                    if (data.deltaXZ > 1) {
                        flag(player, 0);
                        SetBack(player, 1);

                    }
                }
            }
        }
    }
}