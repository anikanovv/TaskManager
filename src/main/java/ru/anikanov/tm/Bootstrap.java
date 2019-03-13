package ru.anikanov.tm;

import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.command.system.HelpCommand;
import ru.anikanov.tm.command.project.*;
import ru.anikanov.tm.command.task.*;
import ru.anikanov.tm.command.user.*;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.repository.ProjectRepository;
import ru.anikanov.tm.repository.TaskRepository;
import ru.anikanov.tm.repository.UserRepository;
import ru.anikanov.tm.service.*;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bootstrap implements ServiceLocator {
    private String currentUser;
    private ITaskRepository taskRepository = new TaskRepository();
    private IProjectRepository projectRepository = new ProjectRepository();
    private IUserRepository userRepository = new UserRepository();
    private final IProjectService projectService = new ProjectService(projectRepository, taskRepository, userRepository);
    private final ITaskService taskService = new TaskService(projectRepository, taskRepository, userRepository);
    private final IUserService userService = new UserService(userRepository);
    private final Scanner scanner = new Scanner(System.in);

    private Map<String, AbstractCommand> commandMap = new HashMap<>();

    public void init() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException {
        initCommands();
        initUsers();
        String commandString;
        do {
            System.out.println("command");
            commandString = scanner.nextLine().trim();
            AbstractCommand command = commandMap.get(commandString);
            if (command != null) if ((command.isSecure()) || (!currentUser.isEmpty())) command.execute();
            else System.out.println("wrong");
        } while (true);
    }

    private void initUsers() {
        userService.persist("admin", passwordHash("admin"), Role.ADMIN);
        userService.persist("user", passwordHash("user"), Role.USER);
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public void initCommands() {
        AbstractCommand[] commands = {
                (new ProjectCreateCommand()),
                (new TaskCreateCommand()),
                (new ProjectReadCommand()),
                (new TaskReadCommand()),
                (new ProjectUpdateCommand()),
                (new TaskUpdateCommand()),
                (new ProjectDeleteCommand()),
                (new TaskDeleteCommand()),
                (new TaskRemoveAllCommand()),
                (new ProjectRemoveAllCommand()),
                (new ProjectCreateCommand()),
                (new HelpCommand()),
                (new UserCreateCommand()),
                (new UserAuthCommand()),
                (new UserRemoveAllCommand()),
                (new UserDeleteCommand()),
                (new UserEndSessionCommand()),
                (new UserReadAllCommand()),
                (new UserReadCommand()),
                (new UserUpdateCommand())
        };
        putToMap(commands, commandMap);
    }

    private void putToMap(AbstractCommand[] commands, Map<String, AbstractCommand> map) {
        for (AbstractCommand command : commands) {
            map.put(command.getName(), command);
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public ITaskService getTaskService() {
        return taskService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public Map<String, AbstractCommand> getCommandMap() {
        return commandMap;
    }

    public String passwordHash(String string) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(string.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

}
