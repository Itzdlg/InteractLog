package me.joshios.interactlog;

import me.joshios.interactlog.event.InteractionEventService;
import me.joshios.interactlog.event.types.InventoryEventListener;
import me.joshios.interactlog.event.types.ItemDropListener;
import me.joshios.interactlog.log.InteractionLogCommand;
import me.joshios.interactlog.user.InteractionUserRepository;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class InteractLog extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Enabling InteractLog " + getDescription().getVersion() + " created by Joshios");

        InteractionUserRepository repository = new InteractionUserRepository();
        InteractionEventService service = new InteractionEventService(repository);

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
