package me.joshios.interactlog.log;

import me.joshios.interactlog.event.action.EventAction;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDateTime;
import java.util.Map;

public class InteractionLog {

    private final ItemStack itemStack;
    private final EventAction eventAction;
    private final LocalDateTime timeStamp;

    public InteractionLog(ItemStack itemStack, EventAction eventAction, LocalDateTime timeStamp) {
        this.itemStack = itemStack;
        this.eventAction = eventAction;
        this.timeStamp = timeStamp;
    }

    public InteractionLog(ItemStack itemStack, EventAction eventAction) {
        this(itemStack, eventAction, LocalDateTime.now());
    }

    public ItemStack getItemStack() {
        return itemStack.clone();
    }

    public EventAction getAction() {
        return eventAction;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getName() {
        return eventAction.getName();
    }

    public Map<String, String> getFields() {
        return eventAction.getFields();
    }
}
