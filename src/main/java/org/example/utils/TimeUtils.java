package org.example.utils;

import java.util.Date;

public class TimeUtils {
    public static int getDaysBetween(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
