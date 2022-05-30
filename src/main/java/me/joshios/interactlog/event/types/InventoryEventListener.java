package me.joshios.interactlog.event.types;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import me.joshios.interactlog.event.InteractionEventService;
import me.joshios.interactlog.event.action.EventAction;
import me.joshios.interactlog.event.action.InventoryCloseEventAction;
import me.joshios.interactlog.event.action.InventoryOpenEventAction;
import me.joshios.interactlog.log.InteractionLog;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryEventListener implements Listener {

    private final InteractionEventService service;

    public InventoryEventListener(InteractionEventService service) {
        this.service = service;
    }

    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent event) {
        HumanEntity humanEntity = event.getPlayer();

        if (!(humanEntity instanceof Player)) {
            return;
        }

        EventAction eventAction = new InventoryOpenEventAction(
                ((Player) humanEntity),
                event.getView().getTitle()
        );

        ItemStack stack = ItemBuilder.from(Material.EMERALD_BLOCK)
                .enchant(Enchantment.DAMAGE_ALL)
                .build();
        InteractionLog log = new InteractionLog(stack, eventAction);

        service.submitLog(humanEntity.getUniqueId(), log);
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event) {
        HumanEntity humanEntity = event.getPlayer();

        if (!(humanEntity instanceof Player)) {
            return;
        }

        EventAction eventAction = new InventoryCloseEventAction(
                ((Player) humanEntity),
                event.getView().getTitle()
        );

        ItemStack stack = ItemBuilder.from(Material.REDSTONE_BLOCK)
                .enchant(Enchantment.DAMAGE_ALL)
                .build();
        InteractionLog log = new InteractionLog(stack, eventAction);

        service.submitLog(humanEntity.getUniqueId(), log);
    }
}
