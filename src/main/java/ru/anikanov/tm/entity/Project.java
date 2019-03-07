package ru.anikanov.tm.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Project {
    private String name;
    private String description;
    private String id = UUID.randomUUID().toString();
    private Date start;
    private Date end;
    //    public List<Task> tasks = new LinkedList<>();
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    public Project(String name, String description, String startDate, String endDate) throws ParseException {
        this.name=name;
        this.description = description;
        setStart(startDate);
        setEnd(endDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStart(String startString) throws ParseException {
        start = format.parse(startString);
    }

    public void setEnd(String endString) throws ParseException {
        end = format.parse(endString);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Проект " + name + " - " + description + " " + id + "\n" + "Start: " + format.format(start) + " end: " + format.format(end) + "\n" + "Задания :\n");
       /* for (Task task : tasks) {
            builder.append(task).append("\n");
        }*/
        return builder.toString();
    }
}
