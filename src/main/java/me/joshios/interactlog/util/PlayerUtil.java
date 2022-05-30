package me.joshios.interactlog.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class PlayerUtil {

    public static Optional<Player> validatePlayer(UUID uuid) {
        if (uuid == null) {
            return Optional.empty();
        }

        Player player = Bukkit.getPlayer(uuid);
        return Optional.ofNullable(player);
    }
}
