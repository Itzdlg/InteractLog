package me.joshios.interactlog.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import me.joshios.interactlog.log.InteractionLog;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SelectActionGui {

    private final InteractionLog log;
    private final Gui gui;

    public SelectActionGui(InteractionLog log) {
        this.log = log;

        this.gui = Gui.gui()
                .rows(3)
                .title(Component.text(log.getName() + " Log"))
                .disableAllInteractions()
                .create();
    }

    public void setup(GuiItem logItem, PaginatedGui originalGui) {
        gui.setItem(12, logItem);
        gui.setItem(14, ItemBuilder.from(log.getItemStack())
                .asGuiItem());

        gui.setItem(0, ItemBuilder.from(Material.ARROW)
                .name(Component.text(ChatColor.GREEN + "Go Back"))
                .asGuiItem(event -> originalGui.open(event.getWhoClicked())));

        gui.getFiller().fill(ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE)
                .name(Component.text(""))
                .asGuiItem());
    }

    public void open(Player player) {
        gui.open(player);
    }
}
