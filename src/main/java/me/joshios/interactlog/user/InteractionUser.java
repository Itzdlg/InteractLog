package me.joshios.interactlog.user;

import me.joshios.interactlog.log.InteractionLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class InteractionUser {

    private final UUID uuid;
    private final List<InteractionLog> logsList;

    public InteractionUser(UUID uuid) {
        this.uuid = uuid;
        this.logsList = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void addLog(InteractionLog log) {
        logsList.add(log);
    }

    public List<InteractionLog> getLogs() {
        return Collections.unmodifiableList(logsList);
    }

}
