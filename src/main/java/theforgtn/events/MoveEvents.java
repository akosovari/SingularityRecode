package theforgtn.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.potion.PotionEffectType;
import theforgtn.Main;
import theforgtn.data.ConfigFile;
import theforgtn.data.PlayerData;

import static java.lang.Math.abs;

public class MoveEvents implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMove(PlayerMoveEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        //Universal values
        //data.ground =! (player.getLocation().subtract(0, 1, 0).getBlock().getType().isAir());
        try {
            data.deltaY = event.getTo().getY() - event.getFrom().getY();
            data.ping = player.getPing();
            data.clientGround = player.isOnGround();
            data.USP_PITCH = player.getLocation().getPitch();
            data.USP_YAW = player.getLocation().getYaw();
            data.deltaXZ = (float) Math.sqrt(Math.pow(event.getTo().getX() - event.getFrom().getX(), 2) + Math.pow(event.getTo().getZ() - event.getFrom().getZ(), 2));
            data.isInWater = player.getLocation().getBlock().getRelative(BlockFace.DOWN).isLiquid() && player.getLocation().getY() - player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation().getY() < 1;
            data.inCreative = player.getGameMode().equals(GameMode.CREATIVE);
            data.jumpBoost = player.hasPotionEffect(PotionEffectType.JUMP);
            data.levitation = player.hasPotionEffect(PotionEffectType.LEVITATION);
            data.inCreative = player.getGameMode() == GameMode.CREATIVE;
            data.TBSpeedXZ = (float) Math.sqrt(Math.pow(abs(abs(player.getLocation().getBlockX()) - abs(data.TBSpeedX)), 2) + Math.pow(abs(abs(player.getLocation().getBlockZ()) - abs(data.TBSpeedZ)), 2));
            data.onBoat = !(abs(abs(player.getLocation().getBlockX()) - Math.abs(data.BoatX)) > 1 || abs(abs(player.getLocation().getBlockY()) - Math.abs(data.BoatY)) > 1 || abs(abs(player.getLocation().getBlockZ()) - Math.abs(data.BoatZ)) > 1);
            if (!player.isInsideVehicle()) {
                data.lastOnFeet = System.currentTimeMillis();
            }
            if (player.isGliding()) {
                data.lastElytra = System.currentTimeMillis();
                data.withElytra = true;
            }
            if (System.currentTimeMillis() - data.lastElytra > 3000) {
                data.withElytra = false;
            }
            if (System.currentTimeMillis() - data.lastVelocityTaken > 2000) {
                data.velXTicks = 0;
                data.velYTicks = 0;
                data.velZTicks = 0;
            }
        } catch (Exception e){
            if(ConfigFile.debug){
                Main.getInstance().getLogger().warning("| Generated an exception [" + e.getCause() + "]");
            }
        }

    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void NearGround(PlayerMoveEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        try {
            double expand = 0.3;
            for (double x = -expand; x <= expand; x += expand) {
                for (double z = -expand; z <= expand; z += expand) {
                    if (event.getTo().clone().add(x, -0.5001, z).getBlock().getType() != Material.AIR) {
                        data.NearGround = true;
                        data.groundTicks++;
                        data.airTicks = 0;
                        data.lastOnGround = System.currentTimeMillis();
                        return;
                    }
                }
            }
            data.NearGround = false;
            data.groundTicks = 0;
            data.airTicks++;
        }catch (Exception e){
            if(ConfigFile.debug){
                Main.getInstance().getLogger().warning("| Generated an exception [" + e.getCause() + "]");
            }
        }
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBoat(PlayerMoveEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        try {
            for (Entity e : player.getLocation().getWorld().getEntities()) {
                if (e.getLocation().distance(event.getPlayer().getLocation()) < 1.5) {
                    if (e.getType() == EntityType.BOAT && e.getLocation().getY() < player.getLocation().getY()) {
                        data.BoatX = event.getPlayer().getLocation().getBlockX();
                        data.BoatY = event.getPlayer().getLocation().getBlockY();
                        data.BoatZ = event.getPlayer().getLocation().getBlockZ();
                    }
                }
            }
        }catch (Exception e){
            if(ConfigFile.debug){
                Main.getInstance().getLogger().warning("| Generated an exception [" + e.getCause() + "]");
            }
        }
    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void Positions(PlayerMoveEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        Material mc = event.getPlayer().getLocation().subtract(0, 0, 0).getBlock().getType();
        Material m = event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType();
        try {
            //USP
            if (data.NearGround || data.vanillaGround || (data.clientGround && data.ground) || data.onBoat || m == Material.VINE || m == Material.LADDER || data.SlimePosition || m == Material.TWISTING_VINES || m == Material.WATER || m == Material.LILY_PAD || abs(data.velXTicks) > 2 || abs(data.velYTicks) > 2 || abs(data.velZTicks) > 2 || data.levitation || data.isInWater || data.airTicks < 5) {
                data.USP_X = (float) event.getPlayer().getLocation().getBlockX();
                data.USP_Y = (float) event.getPlayer().getLocation().getBlockY();
                data.USP_Z = (float) event.getPlayer().getLocation().getBlockZ();
            }
            // Vanilla ground
            data.vanillaGround = m != Material.AIR;
            //Slime
            if (m == Material.SLIME_BLOCK) {
                data.SlimeX = (float) event.getPlayer().getLocation().getX();
                data.SlimeZ = (float) event.getPlayer().getLocation().getZ();
                data.SlimePosition = true;
            }
            if (abs(abs(player.getLocation().getX()) - Math.abs(data.SlimeX)) > 3 || abs(abs(player.getLocation().getZ()) - Math.abs(data.SlimeZ)) > 3) {
                data.SlimePosition = false;
            }
            // OnClimbable
            data.standingInBlock = (!m.isAir() || !mc.isAir());

            // BlockAbove
            if (!(player.getLocation().add(0, 2, 0).getBlock().getType().isAir())
                    || !(player.getLocation().add(1, 2, 0).getBlock().getType().isAir()
                    || !(player.getLocation().add(0, 2, 1).getBlock().getType().isAir()
                    || !(player.getLocation().add(-1, 2, 0).getBlock().getType().isAir()
                    || !(player.getLocation().add(0, 2, -1).getBlock().getType().isAir()
                    || !(player.getLocation().add(-1, 2, -1).getBlock().getType().isAir()
                    || !(player.getLocation().add(1, 2, 1).getBlock().getType().isAir()))))))) {
                data.BlockAboveX = (float) event.getPlayer().getLocation().getX();
                data.BlockAboveZ = (float) event.getPlayer().getLocation().getZ();
                data.blockAbove = true;
            }
            if (abs(abs(player.getLocation().getBlockX()) - Math.abs(data.BlockAboveX)) > 2 || abs(abs(player.getLocation().getBlockZ()) - Math.abs(data.BlockAboveZ)) > 2) {
                data.blockAbove = false;
            }
            // Ice
            if (m == Material.ICE || m == Material.FROSTED_ICE || m == Material.PACKED_ICE || m == Material.BLUE_ICE) {
                data.IceX = (float) event.getPlayer().getLocation().getX();
                data.IceZ = (float) event.getPlayer().getLocation().getZ();
                data.IcePosition = true;
            }
            if (abs(abs(player.getLocation().getBlockX()) - Math.abs(data.IceX)) > 5 || abs(abs(player.getLocation().getBlockZ()) - Math.abs(data.IceZ)) > 5) {
                data.IcePosition = false;
            }
            // Ground
            if (player.getLocation().getY() - player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation().getY() == 1 || player.getLocation().getY() - player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation().getY() == 1.5 || player.getLocation().getY() - player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation().getY() == 1.1875) {
                data.groundX = (float) event.getPlayer().getLocation().getX();
                data.groundY = (float) event.getPlayer().getLocation().getY();
                data.groundZ = (float) event.getPlayer().getLocation().getZ();
                data.ground = true;
                data.usingRiptide = false;
            }
            if (abs(abs(player.getLocation().getBlockX()) - Math.abs(data.groundX)) > 1 || abs(abs(player.getLocation().getBlockZ()) - Math.abs(data.groundZ)) > 1 || abs(abs(player.getLocation().getBlockY()) - Math.abs(data.groundY)) > 0.5) {
                data.ground = false;
            }
            // SP
            if (((data.onBoat || data.NearGround || data.isLevitating || data.usingRiptide || data.withElytra) && System.currentTimeMillis() - data.lastSetBackPosReset > 1000) || System.currentTimeMillis() - Main.PluginEnabled < 1000) {
                data.SetBackX = (float) event.getPlayer().getLocation().getX();
                data.SetBackY = (float) event.getPlayer().getLocation().getY();
                data.SetBackZ = (float) event.getPlayer().getLocation().getZ();
                data.SetBackPos = player.getLocation();

                data.lastSetBackPosReset = System.currentTimeMillis();
            }
        }catch (Exception e){
            if(ConfigFile.debug){
                Main.getInstance().getLogger().warning("| Generated an exception [" + e.getCause() + "]");
            }
        }
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onVelocity(PlayerVelocityEvent event) {
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(event.getPlayer());
        try {
            data.lastVelocityTaken = System.currentTimeMillis();
            data.velXTicks = (int) Math.round(event.getVelocity().getX() * 100);
            data.velYTicks = (int) Math.round(event.getVelocity().getY() * 100);
            data.velZTicks = (int) Math.round(event.getVelocity().getZ() * 100);
        } catch (Exception e){
            if(ConfigFile.debug){
                Main.getInstance().getLogger().warning("| Generated an exception [" + e.getCause() + "]");
            }
        }
    }
}

