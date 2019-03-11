package ru.anikanov.tm;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.command.system.HelpCommand;
import ru.anikanov.tm.command.project.*;
import ru.anikanov.tm.command.task.*;
import ru.anikanov.tm.command.user.*;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.repository.ProjectRepository;
import ru.anikanov.tm.repository.TaskRepository;
import ru.anikanov.tm.repository.UserRepository;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;
import ru.anikanov.tm.service.UserService;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bootstrap {
    private final Scanner scanner = new Scanner(System.in);
    private TaskRepository taskRepository = new TaskRepository();
    private ProjectRepository projectRepository = new ProjectRepository();
    private UserRepository userRepository = new UserRepository();
    public final ProjectService projectService = new ProjectService(projectRepository, taskRepository, userRepository);
    public final TaskService taskService = new TaskService(projectRepository, taskRepository, userRepository);
    public final UserService userService = new UserService(userRepository);
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
            if (command != null) if ((command.isSecure()) || (!currentUser.isEmpty())) command.execute();
            else System.out.println("wrong");
        } while (true);
    }

    public void initUsers() throws UnsupportedEncodingException, NoSuchAlgorithmException {
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
        putToMap(new ProjectCreateCommand(this));

        putToMap(new TaskCreateCommand(this));

        putToMap(new ProjectReadCommand(this));

        putToMap(new TaskReadCommand(this));

        putToMap(new ProjectUpdateCommand(this));

        putToMap(new TaskUpdateCommand(this));

        putToMap(new ProjectDeleteCommand(this));

        putToMap(new TaskDeleteCommand(this));

        putToMap(new TaskRemoveAllCommand(this));

        putToMap(new ProjectRemoveAllCommand(this));

        putToMap(new ProjectCreateCommand(this));

        putToMap(new HelpCommand(this));

        putToMap(new UserCreateCommand(this));

        putToMap(new UserAuthCommand(this));

        putToMap(new UserRemoveAllCommand(this));

        putToMap(new UserDeleteCommand(this));

        putToMap(new UserEndSessionCommand(this));

        putToMap(new UserReadAllCommand(this));

        putToMap(new UserReadCommand(this));

        putToMap(new UserUpdateCommand(this));
    }

    private void putToMap(AbstractCommand command) {
        commandMap.put(command.getName(), command);
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
