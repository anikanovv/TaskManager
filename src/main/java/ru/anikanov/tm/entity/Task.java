package ru.anikanov.tm.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Task extends AbstractEntity {
    private String name;
    private String description;
    private Date start;
    private Date end;
    private String projectId;
    private String userId;
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    public Task(String projectId, String name, String description, String startDate, String endDate, String userId) throws ParseException {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        start = format.parse(startDate);
        end = format.parse(endDate);
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getTaskName() {
        return name;
    }

    public void setTaskName(String newtask) {
        name = newtask;
    }

    public String getTaskDescription() {
        return description;
    }

    public void setDescription(String newdescrip) {
        description = newdescrip;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getStartDate() {
        return format.format(start);
    }

    public void setStart(String start) throws ParseException {
        this.start = format.parse(start);
    }

    public String getEnd() {
        return format.format(end);
    }

    public void setEnd(String end) throws ParseException {
        this.end = format.parse(end);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return (name + " - " + description + "\n" + "start: " + start + " end: " + end);
    }
}
