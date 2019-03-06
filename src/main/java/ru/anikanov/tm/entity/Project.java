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
        setStart(startDate);
        setEnd(endDate);
        tasks=new LinkedList<>();
//        map=new HashMap<>();
    }
    LinkedList<Task> getTasks(){
        return tasks;
    }
//    void setTasks(LinkedList<Task> newtasks){
//        tasks=newtasks;
//    }

    public String getName() {
        return name;
    }
//    void setName(String newname){
//        name=newname;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStart(String startString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
        start = format.parse(startString);
    }

    public void setEnd(String endString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
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
        StringBuilder builder = new StringBuilder("Проект " + name + " - " + description + "\n" + "Start: " + start + " end: " + end + "\n" + "Задания :\n");
        for (Task task : tasks) {
            builder.append(task).append("\n");
        }
        return builder.toString();
    }
}
