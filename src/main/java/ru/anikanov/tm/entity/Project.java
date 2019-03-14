package ru.anikanov.tm.entity;


import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.enumeration.Status;

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
    private Date startDate;
    @Nullable
    private Date endDate;
    @NotNull
    private String userId;
    @NotNull
    private Status status;
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    public Project(String name, String description, String startDate, String endDate, String userId) throws ParseException {
        this.name=name;
        this.description = description;
        setStart(startDate);
        setEnd(endDate);
        this.userId = userId;
        status = Status.SCHEDULED;
    }

    public String getEnd() {
        return format.format(endDate);
    }

    public String getStart() {
        return format.format(startDate);
    }

    public void setStart(String startString) throws ParseException {
        startDate = format.parse(startString);
    }

    public void setEnd(String endString) throws ParseException {
        endDate = format.parse(endString);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Проект " + name + " - " + description + " " + id + "\n" + "Start: " + format.format(startDate) + " end: " + format.format(endDate) + "\n" + "Задания :\n");
        return builder.toString();
    }
}
