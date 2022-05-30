package me.joshios.interactlog.event.types;

import me.joshios.interactlog.event.InteractionEventService;
import me.joshios.interactlog.event.action.EventAction;
import me.joshios.interactlog.event.action.ItemDropEventAction;
import me.joshios.interactlog.log.InteractionLog;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ItemDropListener implements Listener {

    private final InteractionEventService service;

    public ItemDropListener(InteractionEventService service) {
        this.service = service;
    }

    @EventHandler
    public void onItemDropEvent(ItemSpawnEvent event) {
        Item item = event.getEntity();

        UUID thrower = item.getThrower();
        UUID owner = item.getOwner();

        ItemStack itemStack = item.getItemStack().clone();

        EventAction eventAction = new ItemDropEventAction(
                itemStack,
                owner,
                thrower
        );

        InteractionLog log = new InteractionLog(itemStack, eventAction);

        service.submitLog(thrower, log);

        if (thrower != owner) {
            service.submitSilentLog(owner, log);
        }
    }
}