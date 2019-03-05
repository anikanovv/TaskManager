package ru.anikanov.tm;
//package ru.anikanov.tm;

import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;

public class App {
    private static List<Project> projects = new ArrayList<>();//не нужно
    private static Map<String, Project> referencesProjects = new HashMap<>();
    private static final String CREATE = "create";
    private static final String READALL = "readall";
    private static final String READ = "read";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String HELP = "help";
    private static final String INSERT = "insert";
    private static final String REMOVE = "remove";
    private static final String CHANGE = "change";
    private static final String EXIT = "exit";

    public static void main(String[] args) throws IOException, ParseException {
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
            String projectname = new String();
            if (strings.length > 1) projectname = strings[1];
            switch (opperationname) {
                case CREATE:
                    createProject(projectname);
                    break;
                case READALL:
                    read();
                    break;
                case READ:
                    readProject(projectname);
                    break;
                case UPDATE:
                    updateProject(projectname);
                    break;

                case DELETE:
                    deleteProject(projectname);
                    break;
                case HELP:
                    System.out.println("create для создания проекта\n" + "read для чтения проекта\n" + "update для изменения проекта\n" + "delete для удаления проекта\n");
                    break;
                case EXIT:
                    return;
                default:
                    System.out.println("Ошибка при вводе выражения\n");
            }

        } while (!command.equals("end"));
    }

    private static void read() {
        for (Project project : projects) {
            readProject(project.getName());
        }
    }

    private static void createProject(String name) throws ParseException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите описание проекта");
        String description = reader.readLine();
        if (description.isEmpty()) {
            System.out.println("Введена пустая строка, повторите ввод");
            description = reader.readLine();
        }
        System.out.println("Введите дату начала проекта");
        String startdate = reader.readLine();
        System.out.println("Введите дату окончания проекта");
        String enddate = reader.readLine();
        Project newproject = new Project(name, description, startdate, enddate);
        projects.add(newproject);
        projectID(newproject);
        referencesProjects.put(name, newproject);

    }

     private static void readProject(String name) {
         boolean mapContainsName = referencesProjects.containsKey(name);
         if (mapContainsName) {
             Project project = referencesProjects.get(name);
             System.out.println(project.getName() + " " + project.getDescription() + ":");
             for (Task task : project.tasks) {
                 System.out.println(task.getTaskName() + " " + task.getTaskDescription());
            }
        } else System.out.println("Такого проекта не существует или список его задач пуст");
        System.out.println();


    }

     private static void updateProject(String name) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         boolean mapContainsName = referencesProjects.containsKey(name);
         if (mapContainsName) {
             Project project = referencesProjects.get(name);
             System.out.println("Введите через пробел название операции (help для справки)");
//            Project project = projects.get(references.get(name));
             String opperation = reader.readLine();
//            String[] strings = task.split("( )+");
//            String[] variables = Arrays.copyOfRange(strings, 1, strings.length);
//            String opperation = strings[0].toLowerCase();

            switch (opperation) {
                case INSERT:
                    insertTask(project);
                    return;
                case CHANGE:
                    updateTask(project);
                    return;
                case REMOVE:
                    removeTask(project);
                    return;
                case HELP:
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
        boolean mapContainsName = referencesProjects.containsKey(name);
        if (mapContainsName) {
            Project project = referencesProjects.get(name);
            referencesProjects.remove(name);
        } else System.out.println("Такого проекта не существует\n");
    }

    private static void insertTask(Project project) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите название задачи");
        String taskName = reader.readLine();
        System.out.println("Введите описание задачи");
        String taskDescription = reader.readLine();
        System.out.println("Введите дату начала проекта");
        String startdate = reader.readLine();
        System.out.println("Введите дату окончания проекта");
        String enddate = reader.readLine();
        String projID = project.getID();
        Task newtask = new Task(taskName, taskDescription, startdate, enddate, projID);

    }
    //*

    //    ВЫ ЗДЕСЬ!!!!!!!!!!!!!!
//    /*/

     //
     static void readTask(Project project, String... id) {
        for (String stringId:id) {
            int intid = Integer.parseInt(stringId);
            System.out.println(project.tasks.get(intid).task);
        }

    }

     //
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

    static String projectID(Project project) {

    }
}
