package me.joshios.interactlog.util;

import org.ocpsoft.prettytime.PrettyTime;

import java.time.LocalDateTime;

public class TimeUtil {

    private static PrettyTime timeFormatter;

    static {
        timeFormatter = new PrettyTime();
    }

    public static String format(LocalDateTime time) {
        return timeFormatter.format(time);
    }
}
