package me.joshios.interactlog;

import me.joshios.interactlog.event.EventLogFactory;
import me.joshios.interactlog.event.InteractionEventService;
import me.joshios.interactlog.event.types.InventoryEventListener;
import me.joshios.interactlog.event.types.ItemDropListener;
import me.joshios.interactlog.log.InteractionLogCommand;
import me.joshios.interactlog.user.InteractionUser;
import me.joshios.interactlog.user.InteractionUserRepository;
import me.joshios.interactlog.user.Repository;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class InteractLog extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Enabling InteractLog " + getDescription().getVersion() + " created by Joshios");

        Repository<UUID, InteractionUser> repository = new InteractionUserRepository();
        EventLogFactory eventLogFactory = new EventLogFactory();

        InteractionEventService service = new InteractionEventService(repository, eventLogFactory);

        registerEvents(
                new ItemDropListener(service),
                new InventoryEventListener(service)
        );

        getCommand("logs").setExecutor(new InteractionLogCommand(repository));
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling InteractLog " + getDescription().getVersion() + " created by Joshios");
    }

    private void registerEvents(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}