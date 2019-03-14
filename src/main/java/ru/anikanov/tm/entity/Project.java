package ru.anikanov.tm.entity;


import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@Setter
public class Project extends AbstractEntity {
    @Nullable
    private String name;
    @Nullable
    private String description;
    @NotNull
    private Date start;
    @Nullable
    private Date end;
    @NotNull
    private String userId;
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    public Project(String name, String description, String startDate, String endDate, String userId) throws ParseException {
        this.name=name;
        this.description = description;
        setStart(startDate);
        setEnd(endDate);
        this.userId = userId;
    }

    public String getEnd() {
        return format.format(end);
    }

    public String getStart() {
        return format.format(start);
    }

    public void setStart(String startString) throws ParseException {
        start = format.parse(startString);
    }

    public void setEnd(String endString) throws ParseException {
        end = format.parse(endString);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Проект " + name + " - " + description + " " + id + "\n" + "Start: " + format.format(start) + " end: " + format.format(end) + "\n" + "Задания :\n");
        return builder.toString();
    }
}
