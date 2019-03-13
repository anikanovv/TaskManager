package ru.anikanov.tm.bootstrap;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.api.service.ITermincalService;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.command.project.*;
import ru.anikanov.tm.command.system.AboutCommand;
import ru.anikanov.tm.command.system.HelpCommand;
import ru.anikanov.tm.command.task.*;
import ru.anikanov.tm.command.user.*;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.repository.ProjectRepository;
import ru.anikanov.tm.repository.TaskRepository;
import ru.anikanov.tm.repository.UserRepository;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;
import ru.anikanov.tm.service.TerminalService;
import ru.anikanov.tm.service.UserService;
import ru.anikanov.tm.utils.PasswordHash;

import java.util.HashMap;
import java.util.Map;

public class Bootstrap implements ServiceLocator {
    private String currentUser;
    private ITaskRepository taskRepository = new TaskRepository();
    private IProjectRepository projectRepository = new ProjectRepository();
    private IUserRepository userRepository = new UserRepository();
    private final IProjectService projectService = new ProjectService(projectRepository, taskRepository, userRepository);
    private final ITaskService taskService = new TaskService(projectRepository, taskRepository, userRepository);
    private final IUserService userService = new UserService(userRepository);
    private final ITermincalService termincalService = new TerminalService(this);

    private Map<String, AbstractCommand> commandMap = new HashMap<>();

    public void init() throws Exception {
        initCommands();
        initUsers();
        termincalService.terminalCicle();
    }

    private void initUsers() {
        userService.persist("admin", PasswordHash.makehash("admin"), Role.ADMIN);
        userService.persist("user", PasswordHash.makehash("user"), Role.USER);
    }

    public void initCommands() {
        AbstractCommand[] commands = {
                (new ProjectCreateCommand(this)),
                (new TaskCreateCommand(this)),
                (new ProjectReadCommand(this)),
                (new TaskReadCommand(this)),
                (new ProjectUpdateCommand(this)),
                (new TaskUpdateCommand(this)),
                (new ProjectDeleteCommand(this)),
                (new TaskDeleteCommand(this)),
                (new TaskRemoveAllCommand(this)),
                (new ProjectRemoveAllCommand(this)),
                (new ProjectCreateCommand(this)),
                (new HelpCommand(this)),
                (new UserCreateCommand(this)),
                (new UserAuthCommand(this)),
                (new UserRemoveAllCommand(this)),
                (new UserDeleteCommand(this)),
                (new UserEndSessionCommand(this)),
                (new UserReadAllCommand(this)),
                (new UserReadCommand(this)),
                (new UserUpdateCommand(this)),
                (new AboutCommand(this))
        };
        putToMap(commands, commandMap);
    }

    private void putToMap(AbstractCommand[] commands, Map<String, AbstractCommand> map) {
        for (AbstractCommand command : commands) {
            map.put(command.getName(), command);
        }
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

    @Override
    public ITermincalService getTerminlService() {
        return termincalService;
    }

    @Override
    public String getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(String name) {
        currentUser = name;
    }

    public Map<String, AbstractCommand> getCommandMap() {
        return commandMap;
    }

}
