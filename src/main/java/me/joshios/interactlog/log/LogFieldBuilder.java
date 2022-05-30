package me.joshios.interactlog.log;

import me.joshios.interactlog.util.MaterialFormatter;
import me.joshios.interactlog.util.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class LogFieldBuilder {

    private final Map<String, String> dataMap;

    public LogFieldBuilder() {
        this.dataMap = new LinkedHashMap<>();
    }
    public LogFieldBuilder add(String key, String value) {
        dataMap.put(key, value);

        return this;
    }

    public LogFieldBuilder add(String key, Player value) {
        return add(key, value.getDisplayName());
    }

    public LogFieldBuilder add(String key, UUID value) {
        Optional<Player> playerOptional = PlayerUtil.validatePlayer(value);

        if (playerOptional.isPresent()) {
            return add(key, playerOptional.get());
        }
        else {
            return add(key, "Unknown");
        }
    }

    public LogFieldBuilder add(String key, ItemStack value) {
        ItemMeta meta = value.getItemMeta();
        if (meta != null && meta.hasDisplayName()) {
            return add(key, meta.getDisplayName());
        }

        return add(key, MaterialFormatter.format(value.getType()));
    }

    public Map<String, String> build() {
        return Collections.unmodifiableMap(dataMap);
    }
}
