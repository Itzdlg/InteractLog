package me.joshios.interactlog.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InteractionUserRepository {

    private final List<InteractionUser> userList;

    public InteractionUserRepository() {
        this.userList = new ArrayList<>();
    }

    public void createUser(InteractionUser user) {
        if (isExisting(user.getUuid())) {
            return;
        }

        userList.add(user);
    }

    public void removeUser(UUID uuid) {
        Optional<InteractionUser> user = getUser(uuid);

        user.ifPresent(userList::remove);
    }

    public Optional<InteractionUser> getUser(UUID uuid) {
        if (uuid == null) {
            return Optional.empty();
        }

        return userList.stream().filter(user -> user.getUuid().equals(uuid)).findFirst();
    }

    public boolean isExisting(UUID uuid) {
        return getUser(uuid).isPresent();
    }
}
