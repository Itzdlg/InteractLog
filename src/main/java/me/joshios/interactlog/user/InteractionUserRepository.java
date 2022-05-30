package me.joshios.interactlog.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InteractionUserRepository implements Repository<UUID, InteractionUser> {

    private final List<InteractionUser> userList;

    public InteractionUserRepository() {
        this.userList = new ArrayList<>();
    }

    @Override
    public void add(InteractionUser user) {
        if (exists(user.getUuid())) {
            return;
        }

        userList.add(user);
    }

    @Override
    public void remove(UUID uuid) {
        Optional<InteractionUser> user = get(uuid);

        user.ifPresent(userList::remove);
    }

    @Override
    public Optional<InteractionUser> get(UUID uuid) {
        if (uuid == null) {
            return Optional.empty();
        }

        return userList.stream().filter(user -> user.getUuid().equals(uuid)).findFirst();
    }

    @Override
    public boolean exists(UUID uuid) {
        return get(uuid).isPresent();
    }
}
