package me.joshios.interactlog;

import me.joshios.interactlog.event.InteractionEventService;
import me.joshios.interactlog.event.types.InventoryEventListener;
import me.joshios.interactlog.event.types.ItemDropListener;
import me.joshios.interactlog.log.InteractionLogCommand;
import me.joshios.interactlog.user.InteractionUserRepository;
import org.bukkit.plugin.java.JavaPlugin;

public final class InteractLog extends JavaPlugin {

    @Override
    public void onEnable() {
        InteractionUserRepository repository = new InteractionUserRepository();
        InteractionEventService service = new InteractionEventService(repository);

        getServer().getPluginManager().registerEvents(new ItemDropListener(service), this);
        getServer().getPluginManager().registerEvents(new InventoryEventListener(service), this);
        getCommand("logs").setExecutor(new InteractionLogCommand(repository));
    }

    @Override
    public void onDisable() {

    }
}
