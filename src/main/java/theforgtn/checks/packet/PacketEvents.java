package theforgtn.checks.packet;

import io.netty.channel.*;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import theforgtn.checks.packet.*;
import theforgtn.Main;
import theforgtn.data.PlayerData;

import java.util.HashMap;

public class PacketEvents implements Listener {
    // Register player
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if (Main.getInstance().native_version && Bukkit.getBukkitVersion().contains("1.17")) {
            PlayerData data = Main.getInstance().getDataManager().getDataPlayer(event.getPlayer());
            injectPlayer(event.getPlayer());
            data.mppsEventFired = 0;
        }
    }
    // Remove player
    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        if (Main.getInstance().native_version && Bukkit.getBukkitVersion().contains("1.17")) {
            removePlayer(event.getPlayer());
        }
    }

    private void removePlayer(Player player){
        Channel channel = ((CraftPlayer) player).getHandle().b.a.k;
        channel.eventLoop().submit(()->{
            channel.pipeline().remove(player.getName());
            return null;
        });
    }
    // Inject Player
    private void injectPlayer(Player player) {
        // 1.17 Packets
        if (Bukkit.getBukkitVersion().contains("1.17")) {
            ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {

                @Override
                public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
                    //Bukkit.getServer().getLogger().info("PACKET READ: " + packet.toString());
                    super.channelRead(channelHandlerContext, packet);
                    PacketRecieveEvent(player, packet);
                }

                @Override
                public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {
                    //Bukkit.getServer().getLogger().warning("PACKET WRITE: " + packet.toString());
                    super.write(channelHandlerContext, packet, channelPromise);
                }
            };
            ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().b.a.k.pipeline();
            pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);

        }
        // 1.18 Packets
        if (Bukkit.getBukkitVersion().contains("1.18")) {
            ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {

                @Override
                public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
                    //Bukkit.getServer().getLogger().info("PACKET READ: " + packet.toString());
                    super.channelRead(channelHandlerContext, packet);
                }

                @Override
                public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {
                    //Bukkit.getServer().getLogger().warning("PACKET WRITE: " + packet.toString());
                    super.write(channelHandlerContext, packet, channelPromise);
                }
            };
            ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().b.a.k.pipeline();
            pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
        }
        else {
            ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {

                @Override
                public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
                    //Bukkit.getServer().getLogger().info("PACKET READ: " + packet.toString());
                    super.channelRead(channelHandlerContext, packet);
                }

                @Override
                public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {
                    //Bukkit.getServer().getLogger().warning("PACKET WRITE: " + packet.toString());
                    super.write(channelHandlerContext, packet, channelPromise);
                }
            };
            ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().b.a.k.pipeline();
            pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
        }
    }
    // Packet/s
    private void PacketRecieveEvent(Player player, Object packet) {
        PlayerData data = Main.getInstance().getDataManager().getDataPlayer(player);
        if(packet.toString().contains("PacketPlayInFlying$PacketPlayInPosition")){
            data.mppsEventFired++;
            double time = System.currentTimeMillis();
            if(time - data.mppsLastReset > 1000){
                data.mpps = (int) Math.round(data.mppsEventFired - data.mppsLastEventCount);
                data.mppsLastEventCount = data.mppsEventFired;
                data.mppsLastReset = System.currentTimeMillis();
            }
            data.mppslastPacket = System.currentTimeMillis();
        }
    }

}
