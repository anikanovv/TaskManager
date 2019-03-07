package ru.anikanov.tm;

import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.repository.ProjectRepository;
import ru.anikanov.tm.repository.TaskRepository;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.UUID;

public class Bootstrap {
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
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private TaskRepository taskRepository = new TaskRepository();
    private ProjectRepository projectRepository = new ProjectRepository();
    private ProjectService projectService = new ProjectService(projectRepository, taskRepository);
    private TaskService taskService = new TaskService(projectRepository, taskRepository);

    public void init() throws IOException, ParseException {
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
                    createProject(name);
                    break;
                case READALL:
                    readAllProjects();
                    break;
                case READ:
                    readProject(name);
                    break;
                case UPDATE:
                    updateProject(name);
                    break;
                case READTASK:
                    readTask(name);
                    break;
                case DELETE:
                    removeProject(name);
                    break;
                case INSERT:
                    createTask(name);
                    break;
                case CHANGE:
                    updateTask(name);
                    break;
                case REMOVE:
                    removeTask(name);
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

    private void createProject(String name) throws IOException, ParseException {
        System.out.println("Введите через знак ; описание проекта, дату начала проекта, дату окончания проекта");
        String[] projectInfo = reader.readLine().split(";");
        String id = projectId();
        String description = null;
        String startDate = null;
        String endDate = null;
        if (projectInfo.length > 2) {
            description = projectInfo[0];
            startDate = projectInfo[1];
            endDate = projectInfo[2];
        }
        projectService.persist(id, name, description, startDate, endDate);
    }

    private void createTask(String name) throws IOException, ParseException {
        System.out.println("Введите через знак ; описание задачи, дату начала задачи, дату окончания задачи");
        String[] taskInfo = reader.readLine().split(";");
        String projectId = null;
        String description = null;
        String startDate = null;
        String endDate = null;
        if (taskInfo.length > 3) {
            projectId = taskInfo[0];
            description = taskInfo[1];
            startDate = taskInfo[2];
            endDate = taskInfo[3];
        }
        taskService.persist(projectId, name, description, startDate, endDate);
    }

    private void updateProject(String name) throws ParseException, IOException {
        System.out.println("Введите через знак ; описание проекта, дату начала проекта, дату окончания проекта");
        String[] projectInfo = reader.readLine().split(";");
        String description = null;
        String startDate = null;
        String endDate = null;
        if (projectInfo.length > 2) {
            description = projectInfo[0];
            startDate = projectInfo[1];
            endDate = projectInfo[2];
        }
        projectService.merge(name, description, startDate, endDate);
    }

    private void updateTask(String name) throws IOException, ParseException {
        System.out.println("Введите через знак ; описание задачи, дату начала задачи, дату окончания задачи");
        String[] taskInfo = reader.readLine().split(";");
        String description = null;
        String startDate = null;
        String endDate = null;
        if (taskInfo.length > 2) {
            description = taskInfo[0];
            startDate = taskInfo[1];
            endDate = taskInfo[2];
        }
        taskService.merge(name, description, startDate, endDate);
    }

    private void removeProject(String name) {
        projectService.remove(name);
    }

    private void removeTask(String name) {
        taskService.remove(name);
    }

    private void readProject(String name) {
        System.out.println(projectService.findOne(name));
    }

    private void readAllProjects() {
        projectService.findAll().forEach((o) -> System.out.println(o));
    }

    private void readAllTasks() {
        taskService.findAll().forEach((o) -> System.out.println(o));
    }

    private void readTask(String name) {
        System.out.println(taskService.findOne(name));
    }

    public String projectId() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return (uuid);
    }
}
