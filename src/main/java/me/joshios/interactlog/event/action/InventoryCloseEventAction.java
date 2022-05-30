package me.joshios.interactlog.event.action;

import me.joshios.interactlog.log.LogFieldBuilder;
import org.bukkit.entity.Player;

import java.util.Map;

public record InventoryCloseEventAction(
        Player player,
        String inventoryName
) implements EventAction {
    @Override
    public String getName() {
        return "Inventory Close";
    }

    @Override
    public Map<String, String> getFields() {
        return new LogFieldBuilder()
                .add("Player", player)
                .add("Inventory Name", inventoryName)
                .build();
    }

    @Override
    public boolean isNotifySelfEnabled() {
        return false;
    }
}