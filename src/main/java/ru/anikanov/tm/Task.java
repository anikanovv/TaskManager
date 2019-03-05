package ru.anikanov.tm;

public class Task {
    String task;
    private int id;
    Task(String task){
        this.task=task;
    }
     void setid(int i){
        id=i;
    }
    int getid(){
        return id;
    }
    String getTask(){
        return task;
    }
    void setTask(String newtask){
        task=newtask;
    }
}
