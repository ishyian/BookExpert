package com.revolution.bookexpert.other;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class FormatUtils {
    private FormatUtils() {
        throw new AssertionError();
    }

    public static String getDurationString(int seconds) {
        Date date = new Date(seconds * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat(seconds >= 3600 ? "HH:mm:ss" : "mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formatter.format(date);
    }
}
