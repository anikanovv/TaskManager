package ru.anikanov.tm.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateToString {
    @NotNull
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    @Nullable
    public String dateToString(@Nullable Date date) {
        String stringDate = format.format(date);
        return stringDate;
    }

    @Nullable
    public Date stringToDate(@Nullable String string) throws Exception {

        return format.parse(string);
    }
}
