package me.joshios.interactlog.event.action;

import java.util.Map;

public sealed interface EventAction permits InventoryCloseEventAction, InventoryOpenEventAction, ItemDropEventAction {

    String getName();
    Map<String, String> getFields();
    default boolean isNotifySelfEnabled() { return true; }
    default boolean isClickDisplayEnabled() { return false; }
}
