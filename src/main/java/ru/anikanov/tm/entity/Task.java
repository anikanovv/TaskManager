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
    private int projectID;

    public Task(String name, String description, String startDate, String endDate) throws ParseException {
        this.name = name;
        this.description = description;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
        start = format.parse(startDate);
        end = format.parse(endDate);
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

    void setTaskDescription(String newtask) {
        description = newtask;
    }
}
