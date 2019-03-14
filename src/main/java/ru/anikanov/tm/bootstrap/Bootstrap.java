package ru.anikanov.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.api.service.ITerminalService;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
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

@Getter
@Setter
public class Bootstrap implements ServiceLocator {

    private String currentUser;
    private ITaskRepository taskRepository = new TaskRepository();
    private IProjectRepository projectRepository = new ProjectRepository();
    private IUserRepository userRepository = new UserRepository();
    private final IProjectService projectService = new ProjectService(projectRepository, taskRepository, userRepository);
    private final ITaskService taskService = new TaskService(projectRepository, taskRepository, userRepository);
    private final IUserService userService = new UserService(userRepository);
    private final ITerminalService terminalService = new TerminalService(this);
    private Map<String, AbstractCommand> commandMap = new HashMap<>();

    public void init(Class[] classes) throws Exception {
        initCommands(classes);
        initUsers();
        terminalService.terminalCicle();
    }

    private void initUsers() {
        userService.persist("admin", PasswordHash.makehash("admin"), Role.ADMIN);
        userService.persist("user", PasswordHash.makehash("user"), Role.USER);
    }

    public void initCommands(Class[] classes) throws Exception {
        for (Class c : classes) {
            if (c.getSuperclass().equals(AbstractCommand.class)) {
                AbstractCommand command = (AbstractCommand) c.newInstance();
                command.setBootstrap(this);
                commandMap.put(command.getName(), command);
            }
        }
    }

}
