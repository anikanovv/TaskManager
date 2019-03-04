package ru.anikanov.tm;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Project {
    String name;
    LinkedList<String> tasks;
//    Map map;
    Project(String name){
        this.name=name;
        tasks=new LinkedList<>();
//        map=new LinkedHashMap<String,String>();
    }
    void insertTask(String task){
        tasks.add(task);
    }
}
