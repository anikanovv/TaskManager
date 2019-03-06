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
    private Map<String, Project> referencesProjects = new HashMap<>();
    private Map<String, String> projIDref = new HashMap<>();
    private Map<String, Task> referencesTask = new HashMap<>();
//    static HashSet<String> uuids = new HashSet<>();

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
    private static final String READTASK = "readtask";

    public static void main(String[] args) throws IOException, ParseException {
        App app = new App();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;

        do {
            System.out.println("Введите через пробел название операции + имя проекта (help для вывода справки)");
            command = reader.readLine().trim();
            if (command.isEmpty()) {
                break;
            }
            String[] strings = command.split("( )+");
            String opperationname = strings[0].toLowerCase();
            String name = new String();
            if (strings.length > 1) name = strings[1];
            switch (opperationname) {
                case CREATE:
                    app.createProject(name);
                    break;
                case READALL:
                    app.read();
                    break;
                case READ:
                    app.readProject(name);
                    break;
                case UPDATE:
                    app.updateProject(name);
                    break;
                case READTASK:
                    app.readTask(name);
                    break;

                case DELETE:
                    app.deleteProject(name);
                    break;
                case INSERT:
                    app.insertTask(name);
                    break;
                case CHANGE:
                    app.updateTask(name);
                    break;
                case REMOVE:
                    app.removeTask(name);
                    break;
                case HELP:
                    System.out.println("create + имя для создания проекта\n" + "read + имя для чтения проекта\n" + "readall для чтения всех проектов\n" + "update + имя для изменения проекта\n" + "delete + имя для удаления проекта");
                    System.out.println("insert + имя задачи для создания задачи\n" + "change + имя задачи для изменения задачи\n" + "readtask + имя для чтения задачи\n" + "remove + имя задачи для удаления задачи");
                    System.out.println("exit + для выхода");
                    break;
                case EXIT:
                    return;
                default:
                    System.out.println("Ошибка при вводе выражения\n");
            }

        } while (!command.equals(EXIT));
    }

    private void read() {
        for (Map.Entry<String, Project> entry : referencesProjects.entrySet()) {
            Project project = entry.getValue();
            System.out.println(project);
        }
    }

    private void createProject(String name) throws ParseException, IOException {

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
//        projects.add(newproject);
        String newprojID = projectID(newproject);
        referencesProjects.put(name, newproject);
        projIDref.put(newprojID, name);

    }

    private void readProject(String name) {
         boolean mapContainsName = referencesProjects.containsKey(name);
         if (mapContainsName) {
             Project project = referencesProjects.get(name);
             System.out.println(project.getName() + " " + project.getDescription() + " : ");
             for (Task task : project.tasks) {
                 readTask(task.getTaskName());
            }
        } else System.out.println("Такого проекта не существует или список его задач пуст");
        System.out.println();


    }

    private void updateProject(String name) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         boolean mapContainsName = referencesProjects.containsKey(name);
         if (mapContainsName) {
             Project project = referencesProjects.get(name);
             System.out.println("Введите новое описание проекта");
             String newdescrip = reader.readLine();
             if (!newdescrip.isEmpty()) {
                 project.setDescription(newdescrip);
             }
             System.out.println("Введите новую дату начала проекта");
             String newStartDate = reader.readLine();
             if (!newStartDate.isEmpty()) project.setStart(newStartDate);
             System.out.println("Введите новую дату окончания проекта");
             String newEndDate = reader.readLine();
             if (!newEndDate.isEmpty()) project.setEnd(newEndDate);

        } else {
            System.out.println("Такого проекта не существует\n");
        }

    }

    private void deleteProject(String name) {
        boolean mapContainsName = referencesProjects.containsKey(name);
        if (mapContainsName) {
            Project project = referencesProjects.get(name);
            for (Task task : project.tasks) {
                removeTask(task.getTaskName());
            }
            referencesProjects.remove(name);
        } else System.out.println("Такого проекта не существует\n");
    }

    private void insertTask(String taskName) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите название проекта");
        String projName = reader.readLine();
        System.out.println("Введите описание задачи");
        String taskDescription = reader.readLine();
        System.out.println("Введите дату начала проекта");
        String startdate = reader.readLine();
        System.out.println("Введите дату окончания проекта");
        String enddate = reader.readLine();
        Project project = referencesProjects.get(projName);
        String projID = project.getId();
        Task newtask = new Task(taskName, taskDescription, startdate, enddate, projID);
        referencesTask.put(taskName, newtask);
        project.tasks.add(newtask);
    }

    void readTask(String taskname) {
        Task task = referencesTask.get(taskname);
        System.out.println(task.getTaskName() + " - " + task.getTaskDescription());
    }

     //
     private void updateTask(String taskname) throws IOException {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         System.out.println("Введите новое описание задачи");
         Task task = referencesTask.get(taskname);
         task.setDescription(reader.readLine());

    }

    private void removeTask(String taskname) {
        Task task = referencesTask.get(taskname);
        referencesTask.remove(taskname);
        String projName = projIDref.get(task.getProjectID());
        Project project = referencesProjects.get(projName);
        project.tasks.remove(task);
    }

    static String projectID(Project project) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        /*boolean loop = uuids.contains(uuid);
        while (loop) {
            uuid = UUID.randomUUID().toString().replaceAll("-", "");
            loop = uuids.contains(uuid);
        }*/
        project.setId(uuid);
        return (uuid);
    }
}
