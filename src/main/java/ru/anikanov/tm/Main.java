package ru.anikanov.tm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Set<Project> projects=new HashSet<>();
    static Map<String,Project> references=new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String command="";
        System.out.println("Введите операцию(help для вывода справки)");
        do {
            command=reader.readLine();
            if (command == null) {
                return ;
            }
            switch (command.toLowerCase()){
                case "create": System.out.println("Введите имя проекта");
                    String name=reader.readLine();
                    createProject(name);
                    break;
                case "read":System.out.println("Введите имя проекта");
                    name=reader.readLine();
                    readProject(name);
                    break;
                case "update":System.out.println("Введите имя проекта");
                    name=reader.readLine();
                    updateProject(name);
                    break;
                    case "delete":System.out.println("Введите имя проекта");
                    name=reader.readLine();
                    deleteProject(name);
                    break;
            }

        }while (!command.equals("end"));
    }
    static void createProject(String name){
        Project newproject=new Project(name);
        projects.add(newproject);
        references.put(name,newproject);
    }
    static void readProject(String name){
        for(String task:references.get(name).tasks){
            
        };
    }
    static void updateProject(String name){}
    static void deleteProject(String name){}

}
