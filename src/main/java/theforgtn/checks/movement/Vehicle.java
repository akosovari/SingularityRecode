package theforgtn.checks.movement;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import theforgtn.Actions;
import theforgtn.data.ConfigFile;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import java.util.HashMap;

import static java.lang.Math.abs;

public class Vehicle extends Actions {
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
        if (!ConfigFile.anarchy_mode_enabled)  {
            //Boat
            if (player.getVehicle() instanceof Boat) {
                if (abs(abs(player.getLocation().getBlockX()) - Math.abs(data.USP_X)) > 5 || abs(abs(player.getLocation().getBlockY()) - Math.abs(data.USP_Y)) > 1.5 || abs(abs(player.getLocation().getBlockZ()) - Math.abs(data.USP_Z)) > 5) {
                    if (data.deltaY >= 0) {
                        flag(player, 0);
                        SetBack(player, 4);
                    }
                }
                if (data.IcePosition) {
                    if (data.deltaXZ > 0.4) {
                        flag(player, 0);
                        SetBack(player, 4);
                    } else {
                        if (data.deltaXZ > 0.8) {
                            flag(player, 0);
                            SetBack(player, 4);
                        }
                    }
                }
                //Horse
                if (player.getVehicle() instanceof Horse) {
                    if (data.deltaXZ > 1) {
                        flag(player, 0);
                        SetBack(player, 4);

                    }
                }
                //Pig
                if (player.getVehicle() instanceof Pig) {
                    if (data.deltaXZ > 1) {
                        flag(player, 0);
                        SetBack(player, 4);

                    }
                }
                //Strider
                if (player.getVehicle() instanceof Strider) {
                    if (data.deltaXZ > 1) {
                        flag(player, 0);
                        SetBack(player, 4);

                    }
                }
            }
        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        HashMap<Material, Integer> speedyMaterial = new HashMap<Material, Integer>();
        speedyMaterial.put(Material.ICE,50);
        speedyMaterial.put(Material.PACKED_ICE,50);
        speedyMaterial.put(Material.FROSTED_ICE,50);
        speedyMaterial.put(Material.BLUE_ICE,80);
        // Entity Speeds
        HashMap<EntityType, Integer> speed = new HashMap<EntityType, Integer>();
        speed.put(EntityType.MINECART, ConfigFile.minecart);
        speed.put(EntityType.PIG, ConfigFile.pig);
        speed.put(EntityType.DONKEY, ConfigFile.donkey);
        speed.put(EntityType.HORSE, ConfigFile.horse);
        speed.put(EntityType.SKELETON_HORSE, ConfigFile.skeleton_horse);
        speed.put(EntityType.BOAT, ConfigFile.boat);
        if(ConfigFile.anarchy_mode_enabled) {
            player.getScheduler().runAtFixedRate(Main.getInstance(), scheduledTask -> {
                if (player.isInsideVehicle()) {
                    Block blockBelow = player.getLocation().add(0, -1, 0).getBlock();
                    if (data.speed > speed.getOrDefault(player.getVehicle().getType(), ConfigFile.ev_else)) {
                        if(speedyMaterial.containsKey(blockBelow)){
                            if(data.speed > speedyMaterial.get(blockBelow)){
                                flag(player, 0);
                                SetBack(player, 4);
                            }
                        } else {
                            flag(player, 0);
                            SetBack(player, 4);
                        }

                    }
                }
            }, null, 3L, 1L);
        }
    }
}