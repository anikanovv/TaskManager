package ru.anikanov.tm.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtil {
    @NotNull
    private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
    @NotNull
    private static final java.text.SimpleDateFormat sdf =
            new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Nullable
    public String dateToString(@Nullable final Date date) {
        return format.format(date);
    }

    @Nullable
    public Date stringToDate(@Nullable final String string) throws ParseException {
        return format.parse(string);
    }

    public static Date sqlStringToDate(@Nullable final String string) throws ParseException {
        return sdf.parse(string);
    }
}
