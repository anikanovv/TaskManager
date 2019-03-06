package ru.anikanov.tm.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Task {
    private String name;
    private int id;
    private String description;
    Date start;
    Date end;
    private String projectID;

    public Task(String name, String description, String startDate, String endDate, String projID) throws ParseException {
        this.name = name;
        this.description = description;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
        start = format.parse(startDate);
        end = format.parse(endDate);
        projectID = projID;
    }
     void setid(int i){
        id=i;
    }

    public int getid() {
        return id;
    }

    public String getTaskName() {
        return name;
    }

    void setTaskName(String newtask) {
        name = newtask;
    }

    public String getTaskDescription() {
        return description;
    }

    public void setDescription(String newdescrip) {
        description = newdescrip;
    }

    void setTaskDescription(String newtask) {
        description = newtask;
    }

    public String getProjectID() {
        return projectID;
    }

    @Override
    public String toString() {
        return (name + " - " + description + "\n" + "start: " + start + " end: " + end);
    }
}
