package me.joshios.interactlog.log;

import me.joshios.interactlog.gui.InteractEventGui;
import me.joshios.interactlog.user.InteractionUser;
import me.joshios.interactlog.user.Repository;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class InteractionLogCommand implements CommandExecutor {

    private final Repository<UUID, InteractionUser> repository;

    public InteractionLogCommand(Repository<UUID, InteractionUser> repository) {
        this.repository = repository;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        else if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "You must provide a player username to view their logs.");
            return true;
        }

        Player selectedPlayer = Bukkit.getPlayer(args[0]);
        if (selectedPlayer == null) {
            sender.sendMessage(ChatColor.RED + "Player " + args[0] + " does not exist or is not online.");
            return true;
        }

        Player player = (Player) sender;
        Optional<InteractionUser> userOptional = repository.get(selectedPlayer.getUniqueId());

        userOptional.ifPresentOrElse(user -> {
            InteractEventGui eventGui = new InteractEventGui(selectedPlayer);

            eventGui.setup(user);
            eventGui.open(player);
        }, () -> {
            player.sendMessage(ChatColor.RED + "Player " + selectedPlayer.getDisplayName() + " does not have any active logs.");
        });

        return true;
    }
}
