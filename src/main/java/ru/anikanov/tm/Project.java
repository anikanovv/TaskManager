package ru.anikanov.tm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Project {
    private String name;
    LinkedList<Task> tasks;
    HashMap<String,Integer> map;
    Project(String name){
        this.name=name;
        tasks=new LinkedList<>();
        map=new HashMap<>();
    }
    LinkedList<Task> getTasks(){
        return tasks;
    }
    void setTasks(LinkedList<Task> newtasks){
        tasks=newtasks;
    }
    String getName(){
        return name;
    }
    void setName(String newname){
        name=newname;
    }
}
