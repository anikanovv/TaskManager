package ru.anikanov.tm.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Task {
    private String name;
    private String id;
    private String description;
    private Date start;
    private Date end;
    private String projectId;
    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    public Task(String projectId, String name, String description, String startDate, String endDate) throws ParseException {
        this.name = name;
        this.description = description;

        start = format.parse(startDate);
        end = format.parse(endDate);
        this.projectId = projectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String i) {
        id = i;
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

    public Date getStartDate() {
        return start;
    }

    public void setStart(String start) throws ParseException {
        this.start = format.parse(start);
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(String end) throws ParseException {
        this.end = format.parse(end);
    }

    @Override
    public String toString() {
        return (name + " - " + description + "\n" + "start: " + start + " end: " + end);
    }
}
