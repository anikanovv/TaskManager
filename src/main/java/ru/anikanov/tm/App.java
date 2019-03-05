package ru.anikanov.tm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

 class App {
     private static  List<Project> projects = new ArrayList<>();
     private static  Map<String, Integer> references = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;

        do {
            System.out.println("Введите через пробел название операции + имя проекта (help для вывода справки)");
            command = reader.readLine().trim();
            if (command.isEmpty()) {
                return;
            }
            String[] strings = command.split("( )+");
            String opperationname = strings[0].toLowerCase();
            String projectname = strings[1];
            switch (opperationname) {
                case "create":
                    createProject(projectname);
                    break;
                case "read":
                    readProject(projectname);
                    break;
                case "update":
                    updateProject(projectname);
                    break;

                case "delete":
                    deleteProject(projectname);
                    break;
                case "help":
                    System.out.println("create для создания проекта\n" + "read для чтения проекта\n" + "update для изменения проекта\n" + "delete для удаления проекта\n");
                    break;
                case "end":
                    return;
                default:
                    System.out.println("Ошибка при вводе выражения\n");
            }

        } while (!command.equals("end"));
    }

     private static void createProject(String name) {
        Project newproject = new Project(name);
        projects.add(newproject);
        int projid = projects.size() - 1;
        references.put(name, projid);
    }

     private static void readProject(String name) {
        boolean mapContainsName = references.containsKey(name);
        int projid = references.get(name);
        boolean projTasksIsNotEmpty = projects.get(projid).tasks.size() > 0;
        if ((mapContainsName) && (projTasksIsNotEmpty)) {
            System.out.println(name + ":");
            for (Task task : projects.get(references.get(name)).tasks) {
                System.out.println(task.getid() + " " + task.task);
            }
        } else System.out.println("Такого проекта не существует или список его задач пуст");
        System.out.println();

    }

     private static void updateProject(String name) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean mapContainsName = references.containsKey(name);
        boolean projSizeBigger = projects.size() > references.get(name);
        if (mapContainsName && (projSizeBigger)) {
            System.out.println("Введите через пробел название операции + задачу (help для справки)");
            Project project = projects.get(references.get(name));
            String task = reader.readLine();
            String[] strings = task.split("( )+");
            String[] variables = Arrays.copyOfRange(strings, 1, strings.length);
            String opperation = strings[0].toLowerCase();

            switch (opperation) {
                case "insert":
                    insertTask(project, variables);
                    return;
                case "update":
                    updateTask(project, variables);
                    return;
                case "remove":
                    removeTask(project, variables);
                    return;
                case "help":
                    System.out.println("insert + задача для создания задания\n" + "update + idзадачи для изменения задания\n" + "delete + idзадачи для удаления проекта");
                    break;
                default:
                    System.out.println("Ошибка при вводе выражения\n");
            }


        } else {
            System.out.println("Такого проекта не существует\n");
        }

    }

     private static void deleteProject(String name) {
        boolean mapContainsName = references.containsKey(name);
        boolean projSizeBigger = projects.size() > references.get(name);
        if (mapContainsName && projSizeBigger) {
            int id = references.get(name);
            projects.remove(id);
            references.remove(name);
            for (int i = id; i < projects.size(); i++) {
                String projName = projects.get(i).getName();
                references.put(projName, i);
            }
        } else System.out.println("Такого проекта не существует\n");
    }

     private static void insertTask(Project project, String... task) {
        for (String taskName:task) {
            Task newtask = new Task(taskName);
            project.tasks.add(newtask);
            int taskid = project.tasks.size() - 1;
            project.map.put(taskName, taskid);
            newtask.setid(taskid);
        }

    }

     //
     static void readTask(Project project, String... id) {
        for (String stringId:id) {
            int intid = Integer.parseInt(stringId);
            System.out.println(project.tasks.get(intid).task);
        }

    }

     private  static void updateTask(Project project, String... id) throws IOException {
        for (String stringId:id) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите новую задачу");
            int intid = Integer.parseInt(stringId);
            project.tasks.get(intid).task = reader.readLine();
        }

    }

     private static void removeTask(Project project, String... id) {
        Task[] forrem = new Task[id.length];
        for (int i = 0; i < id.length; i++) {
            int remId = Integer.parseInt(id[i]);
            forrem[i] = project.tasks.get(remId);
        }
        for (Task task:forrem) {
            project.tasks.remove(task);
        }
        for (int i = 0; i < project.tasks.size(); i++) {
            project.tasks.get(i).setid(i);
        }
    }
}
