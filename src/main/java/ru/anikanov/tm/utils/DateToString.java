package ru.anikanov.tm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateToString {
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    public String dateToString(Date date) {
        String stringDate = format.format(date);
        return stringDate;
    }

    public Date stringToDate(String string) throws Exception {

        return format.parse(string);
    }
}
