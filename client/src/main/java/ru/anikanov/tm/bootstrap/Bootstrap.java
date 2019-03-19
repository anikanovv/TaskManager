package ru.anikanov.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

    @Nullable
    private String currentUser;
    @NotNull
    private ITaskRepository taskRepository = new TaskRepository();
    @NotNull
    private IProjectRepository projectRepository = new ProjectRepository();
    @NotNull
    private IUserRepository userRepository = new UserRepository();
    /*@NotNull
    private ProjectEndPointService projectEndPointService=new ProjectEndPointService();
    @NotNull
    private TaskEndPointService taskEndPointService=new TaskEndPointService();
    @NotNull
    private UserEndPointService userEndPointService=new UserEndPointService();*/
    @NotNull
    private final IProjectService projectService = new ProjectService(projectRepository, taskRepository, userRepository);
    @NotNull
    private final ITaskService taskService = new TaskService(projectRepository, taskRepository, userRepository);
    @NotNull
    private final IUserService userService = new UserService(userRepository);
    @NotNull
    private final ITerminalService terminalService = new TerminalService(this);
    @NotNull
    private Map<String, AbstractCommand> commandMap = new HashMap<>();

    public void init(@NotNull final Class[] classes) throws Exception {
        initCommands(classes);
        initUsers();
        terminalService.terminalCicle();
    }

    private void initUsers() {
        userService.persist("admin", PasswordHash.makehash("admin"), Role.ADMIN);
        userService.persist("user", PasswordHash.makehash("user"), Role.USER);
    }

    private void initCommands(@NotNull final Class[] classes) throws Exception {
        for (@Nullable Class c : classes) {
            if ((c != null) && (c.getSuperclass().equals(AbstractCommand.class))) {
                @NotNull AbstractCommand command = (AbstractCommand) c.newInstance();
                command.setBootstrap(this);
                commandMap.put(command.getName(), command);
            }
        }
    }

}
