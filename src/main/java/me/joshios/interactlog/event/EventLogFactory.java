package me.joshios.interactlog.event;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.GuiItem;
import me.joshios.interactlog.gui.SelectActionGui;
import me.joshios.interactlog.log.InteractionLog;
import me.joshios.interactlog.util.TextComponentBuilder;
import me.joshios.interactlog.util.TimeUtil;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EventLogFactory {

    public ItemStack assembleStack(InteractionLog log) {
        List<String> loreLines = getLines(log.getFields());

        String timeData = TimeUtil.format(log.getTimeStamp());
        loreLines.add("");
        loreLines.add(ChatColor.DARK_GRAY + "Occurred: " + timeData);

        return ItemBuilder.from(log.getItemStack())
                .name(Component.text(ChatColor.RED + log.getName()))
                .flags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE)
                .setLore(loreLines)
                .build();
    }

    public TextComponent assembleMessage(UUID uuid, InteractionLog log) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null) {
            return null;
        }

        return new TextComponentBuilder()
                .text(ChatColor.DARK_RED + "" + ChatColor.BOLD + "INTERACT " + ChatColor.RED + "➜ Player " + player.getDisplayName() + " activated ")
                .assemble()
                .text(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + log.getName().toUpperCase())
                .hover(String.join("\n", getLines(log.getFields())))
                .click("/logs " + player.getName())
                .assemble()
                .text(ChatColor.RED + " event")
                .build();
    }
    public List<String> getLines(Map<String, String> fields) {
        List<String> lines = new ArrayList<>();

        fields.forEach((key, value) -> {
            String loreLine = ChatColor.GRAY + key + ": " + ChatColor.RED + value;

            lines.add(loreLine);
        });

        return lines;
    }

}
