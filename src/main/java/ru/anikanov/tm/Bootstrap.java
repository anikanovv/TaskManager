package ru.anikanov.tm;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.command.project.*;
import ru.anikanov.tm.command.task.*;
import ru.anikanov.tm.repository.ProjectRepository;
import ru.anikanov.tm.repository.TaskRepository;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bootstrap {
    private static final String CREATEPROJECT = "createproject";
    private static final String CREATETASK = "createtask";
    private static final String READALL = "readall";
    private static final String READPROJECT = "readproject";
    private static final String READTASK = "readtask";
    private static final String UPDATEPROJECT = "updateprject";
    private static final String UPDATETASK = "updatetask";
    private static final String DELETEPROJECT = "deleteproject";
    private static final String DELETETASK = "deletetask";
    private static final String REMOVEALLTASKS = "removealltasks";
    private static final String REMOVEALLPROJECTS = "removeallprojects";
    private static final String HELP = "help";
    private static final String EXIT = "exit";
    private Scanner scanner = new Scanner(System.in);
    private TaskRepository taskRepository = new TaskRepository();
    private ProjectRepository projectRepository = new ProjectRepository();
    public ProjectService projectService = new ProjectService(projectRepository, taskRepository);
    public TaskService taskService = new TaskService(projectRepository, taskRepository);

    private Map<String, AbstractCommand> commandMap = new HashMap<>();

    public void init() throws IOException, ParseException {
        initCommands();
        String commandString = null;
        do {
            System.out.println("command");
            commandString = scanner.next().trim();
            AbstractCommand command = commandMap.get(commandString);
            if (command != null) command.execute(scanner.next());
        } while (true);
    }

    public void initCommands() {
        commandMap.put(CREATEPROJECT, new ProjectCreateCommand(this));
        commandMap.put(CREATETASK, new TaskCreateCommand(this));
//        commandMap.put(READALL,new ProjectCreateCommand(projectService,taskService));
        commandMap.put(READPROJECT, new ProjectReadCommand(this));
        commandMap.put(READTASK, new TaskReadCommand(this));
        commandMap.put(UPDATEPROJECT, new ProjectUpdateCommand(this));
        commandMap.put(UPDATETASK, new TaskUpdateCommand(this));
        commandMap.put(DELETEPROJECT, new ProjectDeleteCommand(this));
        commandMap.put(DELETETASK, new TaskDeleteCommand(this));
        commandMap.put(REMOVEALLTASKS, new TaskRemoveAllCommand(this));
        commandMap.put(REMOVEALLPROJECTS, new ProjectRemoveAllCommand(this));
        commandMap.put(HELP, new ProjectCreateCommand(this));
        commandMap.put(EXIT, new ProjectCreateCommand(this));
    }
}
