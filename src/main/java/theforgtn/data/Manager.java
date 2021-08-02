package theforgtn.data;

import org.bukkit.Bukkit;

import java.util.HashSet;
import java.util.Set;

public class Manager {

    private final Set<PlayerData> dataSet = new HashSet<>();

    public Manager() {
        Bukkit.getOnlinePlayers().forEach(this::add);
    }

    public PlayerData getDataPlayer(org.bukkit.entity.Player player) {
        return dataSet.stream().filter(dataPlayer -> dataPlayer.player == player).findFirst().orElse(null);
    }

    public void add(org.bukkit.entity.Player player) {
        dataSet.add(new PlayerData(player));
    }

    public void remove(org.bukkit.entity.Player player) {
        dataSet.removeIf(dataPlayer -> dataPlayer.player == player);
    }
}