package ru.anikanov.tm.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Project {
    private String name;
    private String description;
    private String id;
    Date start;
    Date end;
    public LinkedList<Task> tasks;

    //    HashMap<String,Integer> map;
    public Project(String name, String description, String startDate, String endDate) throws ParseException {
        this.name=name;
        this.description = description;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
        start = format.parse(startDate);
        end = format.parse(endDate);
        tasks=new LinkedList<>();
//        map=new HashMap<>();
    }
    LinkedList<Task> getTasks(){
        return tasks;
    }
    void setTasks(LinkedList<Task> newtasks){
        tasks=newtasks;
    }

    public String getName() {
        return name;
    }
    void setName(String newname){
        name=newname;
    }

    public String getDescription() {
        return description;
    }
}
