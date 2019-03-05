package ru.anikanov.tm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Project {
    String name;
    LinkedList<Task> tasks;
    HashMap<String,Integer> map;
//    Map map;
    Project(String name){
        this.name=name;
        tasks=new LinkedList<>();
//        map=new LinkedHashMap<String,String>();
        map=new HashMap<>();
    }
    void insertTask(String... task){
        for (int i=0;i<task.length;i++){
            Task newtask=new Task(task[i]);
            tasks.add(newtask);
            map.put(task[i],new Integer(tasks.size()-1));
            newtask.setid(new Integer(tasks.size()-1));
        }

    }
    void readTask(String... id){
//        String task=map.get(id);
//        tasks.get(id);
        for (int i=0;i<id.length;i++){
            System.out.println(tasks.get(Integer.parseInt(id[i])).task);
        }

    }
    void updateTask(String... id) throws IOException {
        for (int i=0;i<id.length;i++){
            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите новую задачу");
            tasks.get(Integer.parseInt(id[i])).task=reader.readLine();
        }

    }
    void removeTask(String... id)  {
        tasks.remove(id);
        Task [] forrem=new Task[id.length];
        for (int i=0;i<id.length;i++){
            forrem[i]=tasks.get(Integer.parseInt(id[i]));
        }
//        int i=0;
        for (int i=0;i<forrem.length;i++){
            tasks.remove(forrem[i]);
        }
        for(  int i=0;i<tasks.size();i++){
            tasks.get(i).setid(i);
        }
    }
}
