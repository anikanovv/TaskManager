package ru.anikanov.tm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int projcount=0;
    static List<Project> projects=new ArrayList<>();
    static Map<String,Integer> references=new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String command="";

        do {
            System.out.println("Введите через пробел название операции + имя проекта (help для вывода справки)");
            command=reader.readLine().trim();
            if (command == null) {
                return ;
            }
            String [] strings=command.split("( )+");
            switch (strings[0].toLowerCase()){
                case "create":
//                    System.out.println("Введите имя проекта");
//                    String name=reader.readLine();
                    createProject(strings[1]);
                    System.out.println();
                    break;
                case "read":
//                    System.out.println("Введите имя проекта");
//                    name=reader.readLine();
                    System.out.println(strings[1]+":");
                    readProject(strings[1]);
                    System.out.println();
                    break;
                case "update":
//                    System.out.println("Введите имя проекта");
//                    name=reader.readLine();
                    updateProject(strings[1]);
                    System.out.println();
                    break;

                    case "delete":
//                        System.out.println("Введите имя проекта");
//                    name=reader.readLine();
                    deleteProject(strings[1]);
                        System.out.println();
                    break;
                case "help":
                    System.out.println("create для создания проекта\n"+"read для чтения проекта\n"+"update для изменения проекта\n"+"delete для удаления проекта");
                    System.out.println();
                    break;
                case "end":return;
                    default: System.out.println("Ошибка при вводе выражения\n");
            }

        }while (!command.equals("end"));
    }
    static void createProject(String name){
        Project newproject=new Project(name);
        projects.add(newproject);
        references.put(name,projcount);
        projcount++;
    }
    static void readProject(String name){
        if ((references.containsKey(name))&&(projects.get(references.get(name)).tasks.size()>0)){

            for (Task task: projects.get(references.get(name)).tasks){
                System.out.println(task.getid()+" "+task.task);
            }
        }

    }
    static void updateProject(String name) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

        if (references.containsKey(name)){
            if (projects.size()>references.get(name)){
                System.out.println("Введите через пробел название операции + задачу (help для справки)");
                Project project=projects.get(references.get(name));
                while (true){
                String task=reader.readLine();
                String[] strings=task.split("( )+");

                switch (strings[0].toLowerCase()){
                    case "insert":
//                        System.out.println("Введите имя задачи");
//                        task=reader.readLine();
                        String [] newstrings= Arrays.copyOfRange(strings,1,strings.length);
                        project.insertTask(newstrings);
                        return;
                   /* case "read":
//                        System.out.println("Введите имя проекта");
//                        task=reader.readLine();
                        newstrings= Arrays.copyOfRange(strings,1,strings.length);
                        project.readTask(newstrings);
                        break;*/
                    case "update":
//                        System.out.println("Введите имя проекта");
//                        task=reader.readLine();
                        newstrings= Arrays.copyOfRange(strings,1,strings.length);
                        project.updateTask(newstrings);
                        return;
                    case "remove":
//                        System.out.println("Введите имя проекта");
                        newstrings= Arrays.copyOfRange(strings,1,strings.length);
                        project.removeTask(newstrings);
                        return;
                    case "help":
                        System.out.println("insert + задача для создания задания\n"+"update + idзадачи для изменения задания\n"+"delete + idзадачи для удаления проекта");
                        break;
                    default: System.out.println("Ошибка при вводе выражения\n");
                }}
            }


        }else {
            System.out.println("Нет такого проекта");
            return;
        }

    }
    static void deleteProject(String name){
        int id=references.get(name);
        projects.remove(id);
        references.remove(name);
        projcount--;
        for (int i=id;i<projects.size();i++){
            references.put(projects.get(i).name,i);
        }
    }

}
