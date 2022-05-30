package me.joshios.interactlog.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import me.joshios.interactlog.event.EventLogFactory;
import me.joshios.interactlog.log.InteractionLog;
import me.joshios.interactlog.user.InteractionUser;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InteractEventGui {

    private final PaginatedGui gui;

    public InteractEventGui(Player selectedPlayer) {
        this.gui = Gui.paginated()
                .rows(6)
                .title(Component.text("Logs of " + selectedPlayer.getDisplayName()))
                .disableAllInteractions()
                .pageSize(45)
                .create();

        setupPaginator();
    }

    public void setup(InteractionUser user) {
        List<InteractionLog> logs = new ArrayList<>(user.getLogs());
        Collections.reverse(logs);

        EventLogFactory itemFactory = new EventLogFactory();

        for (InteractionLog log : logs) {
            ItemStack stack = itemFactory.assembleStack(log);
            GuiItem guiItem = new GuiItem(stack);

            if (log.getAction().isClickDisplayEnabled()) {
                SelectActionGui interactEventGui = new SelectActionGui(log);

                interactEventGui.setup(new GuiItem(stack.clone()), gui);
                guiItem.setAction(event -> interactEventGui.open((Player) event.getWhoClicked()));
            }

            gui.addItem(guiItem);
        }
    }

    public void open(Player player) {
        gui.open(player);
    }

    private void setupPaginator() {
        gui.setItem(6, 3, ItemBuilder.from(Material.PAPER)
                .name(Component.text("Previous"))
                .asGuiItem(event -> gui.previous()));

        gui.setItem(6, 7, ItemBuilder.from(Material.PAPER)
                .name(Component.text("Next"))
                .asGuiItem(event -> gui.next()));

        gui.getFiller().fillBottom(ItemBuilder.from(Material.GRAY_STAINED_GLASS_PANE)
                .name(Component.text(""))
                .asGuiItem());
    }
}
