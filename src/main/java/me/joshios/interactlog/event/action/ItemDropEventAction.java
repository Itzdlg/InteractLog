package me.joshios.interactlog.event.action;

import me.joshios.interactlog.log.LogFieldBuilder;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public record ItemDropEventAction(
        ItemStack itemStack,
        UUID owner,
        UUID thrower
) implements EventAction {

    @Override
    public String getName() {
        return "Item Drop";
    }

    @Override
    public Map<String, String> getFields() {
        return new LogFieldBuilder()
                .add("Item Name", itemStack)
                .add("Thrower", thrower)
                .add("Owner", owner)
                .build();
    }

    @Override
    public boolean isClickDisplayEnabled() {
        return true;
    }
}
