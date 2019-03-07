package ru.anikanov.tm;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.command.project.*;
import ru.anikanov.tm.command.task.*;
import ru.anikanov.tm.repository.ProjectRepository;
import ru.anikanov.tm.repository.TaskRepository;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bootstrap {
    private Scanner scanner = new Scanner(System.in);
    private TaskRepository taskRepository = new TaskRepository();
    private ProjectRepository projectRepository = new ProjectRepository();
    public ProjectService projectService = new ProjectService(projectRepository, taskRepository);
    public TaskService taskService = new TaskService(projectRepository, taskRepository);
    private String currentUser;

    private Map<String, AbstractCommand> commandMap = new HashMap<>();

    public void init() throws ParseException {
        initCommands();
        String commandString;
        do {
            System.out.println("command");
            commandString = scanner.next().trim();
            AbstractCommand command = commandMap.get(commandString);
            if (command != null) command.execute(scanner.next());
        } while (true);
    }

    public void initCommands() {
        ProjectCreateCommand projectCreateCommand = new ProjectCreateCommand(this);
        commandMap.put(projectCreateCommand.getName(), projectCreateCommand);
        TaskCreateCommand taskCreateCommand = new TaskCreateCommand(this);
        commandMap.put(taskCreateCommand.getName(), taskCreateCommand);
        ProjectReadCommand projectReadCommand = new ProjectReadCommand(this);
        commandMap.put(projectReadCommand.getName(), projectReadCommand);
        TaskReadCommand taskReadCommand = new TaskReadCommand(this);
        commandMap.put(taskReadCommand.getName(), taskReadCommand);
        ProjectUpdateCommand projectUpdateCommand = new ProjectUpdateCommand(this);
        commandMap.put(projectUpdateCommand.getName(), projectUpdateCommand);
        TaskUpdateCommand taskUpdateCommand = new TaskUpdateCommand(this);
        commandMap.put(taskUpdateCommand.getName(), taskUpdateCommand);
        ProjectDeleteCommand projectDeleteCommand = new ProjectDeleteCommand(this);
        commandMap.put(projectDeleteCommand.getName(), projectDeleteCommand);
        TaskDeleteCommand taskDeleteCommand = new TaskDeleteCommand(this);
        commandMap.put(taskDeleteCommand.getName(), taskDeleteCommand);
        TaskRemoveAllCommand taskRemoveAllCommand = new TaskRemoveAllCommand(this);
        commandMap.put(taskRemoveAllCommand.getName(), taskRemoveAllCommand);
        ProjectRemoveAllCommand projectRemoveAllCommand = new ProjectRemoveAllCommand(this);
        commandMap.put(projectRemoveAllCommand.getName(), projectRemoveAllCommand);
        ProjectCreateCommand projectCreateCommand1 = new ProjectCreateCommand(this);
        commandMap.put(projectCreateCommand1.getName(), projectCreateCommand1);
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

}
