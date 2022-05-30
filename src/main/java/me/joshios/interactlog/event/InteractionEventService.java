package me.joshios.interactlog.event;

import me.joshios.interactlog.log.InteractionLog;
import me.joshios.interactlog.user.InteractionUser;
import me.joshios.interactlog.user.Repository;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class InteractionEventService {

    private final Repository<UUID, InteractionUser> repository;
    private final EventLogFactory eventLogFactory;

    public InteractionEventService(Repository<UUID, InteractionUser> repository) {
        this.repository = repository;
        this.eventLogFactory = new EventLogFactory();
    }

    public void submitLog(UUID uuid, InteractionLog log) {
        if (uuid == null) {
            return;
        }

        submitSilentLog(uuid, log);

        TextComponent component = eventLogFactory.assembleMessage(uuid, log);

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.hasPermission("interactlog.notify")) {
                continue;
            }
            else if (!log.getAction().isNotifySelfEnabled() && uuid.equals(player.getUniqueId())) {
                continue;
            }

            player.spigot().sendMessage(component);
        }
    }

    public void submitSilentLog(UUID uuid, InteractionLog log) {
        if (uuid == null) {
            return;
        }

        repository.get(uuid).ifPresentOrElse(user -> {
            user.addLog(log);
        }, () -> {
            InteractionUser user = new InteractionUser(uuid);
            user.addLog(log);

            repository.add(user);
        });
    }

}
