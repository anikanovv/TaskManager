package ru.anikanov.tm;

import ru.anikanov.tm.Enum.Role;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.command.HelpCommand;
import ru.anikanov.tm.command.project.*;
import ru.anikanov.tm.command.task.*;
import ru.anikanov.tm.command.user.*;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.repository.ProjectRepository;
import ru.anikanov.tm.repository.TaskRepository;
import ru.anikanov.tm.repository.UserRepository;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;
import ru.anikanov.tm.service.UserService;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bootstrap {
    private Scanner scanner = new Scanner(System.in);
    private TaskRepository taskRepository = new TaskRepository();
    private ProjectRepository projectRepository = new ProjectRepository();
    private UserRepository userRepository = new UserRepository();
    public ProjectService projectService = new ProjectService(projectRepository, taskRepository);
    public TaskService taskService = new TaskService(projectRepository, taskRepository);
    public UserService userService = new UserService(userRepository);
    private String currentUser;

    public Map<String, AbstractCommand> commandMap = new HashMap<>();

    public void init() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException {
        initCommands();
        initUsers();
        String commandString;
        do {
            System.out.println("command");
            commandString = scanner.nextLine().trim();
            AbstractCommand command = commandMap.get(commandString);
            if (command != null) command.execute();
            else System.out.println("wrong");
        } while (true);
    }

    public void initUsers() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        userService.persist("admin", "admin", Role.ADMIN);
        userService.persist("user", "user", Role.USER);
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
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

        HelpCommand helpCommand = new HelpCommand(this);
        commandMap.put(helpCommand.getName(), helpCommand);

        UserCreateCommand userCreateCommand = new UserCreateCommand(this);
        commandMap.put(userCreateCommand.getName(), userCreateCommand);

        UserAuthCommand userAuthCommand = new UserAuthCommand(this);
        commandMap.put(userAuthCommand.getName(), userAuthCommand);

        UserDeleteAllCommand userDeleteAllCommand = new UserDeleteAllCommand(this);
        commandMap.put(userDeleteAllCommand.getName(), userDeleteAllCommand);

        UserDeleteCommand userDeleteCommand = new UserDeleteCommand(this);
        commandMap.put(userDeleteCommand.getName(), userDeleteCommand);

        UserEndSessionCommand userEndSessionCommand = new UserEndSessionCommand(this);
        commandMap.put(userEndSessionCommand.getName(), userEndSessionCommand);

        UserReadAllCommand userReadAllCommand = new UserReadAllCommand(this);
        commandMap.put(userReadAllCommand.getName(), userReadAllCommand);

        UserReadCommand userReadCommand = new UserReadCommand(this);
        commandMap.put(userReadCommand.getName(), userReadCommand);

        UserUpdateCommand userUpdateCommand = new UserUpdateCommand(this);
        commandMap.put(userUpdateCommand.getName(), userUpdateCommand);

    }

}
