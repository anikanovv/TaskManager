package ru.anikanov.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.endpoint.IProjectEndPoint;
import ru.anikanov.tm.api.endpoint.ITaskEndPoint;
import ru.anikanov.tm.api.endpoint.IUserEndPoint;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.endpoint.ProjectEndPoint;
import ru.anikanov.tm.endpoint.TaskEndPoint;
import ru.anikanov.tm.endpoint.UserEndPoint;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.repository.ProjectRepository;
import ru.anikanov.tm.repository.TaskRepository;
import ru.anikanov.tm.repository.UserRepository;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;
import ru.anikanov.tm.service.UserService;
import ru.anikanov.tm.utils.PasswordHash;

import javax.xml.ws.Endpoint;

@Getter
@Setter
public class Bootstrap implements ServiceLocator {

    @Nullable
    private Session currentSession;
    @NotNull
    private ITaskRepository taskRepository = new TaskRepository();
    @NotNull
    private IProjectRepository projectRepository = new ProjectRepository();
    @NotNull
    private IUserRepository userRepository = new UserRepository();
    @NotNull
    private ITaskEndPoint taskEndPoint = new TaskEndPoint(this);
    @NotNull
    private IProjectEndPoint projectEndPoint = new ProjectEndPoint(this);
    @NotNull
    private IUserEndPoint userEndPoint = new UserEndPoint(this);
    @NotNull
    private final IProjectService projectService = new ProjectService(projectRepository, taskRepository, userRepository);
    @NotNull
    private final ITaskService taskService = new TaskService(projectRepository, taskRepository, userRepository);
    @NotNull
    private final IUserService userService = new UserService(userRepository);

    public void init() {
        initUsers();
        Endpoint.publish("http://localhost:8080/ProjectEndpoint?wsdl",projectEndPoint);
        Endpoint.publish("http://localhost:8080/TaskEndpoint?wsdl",taskEndPoint);
        Endpoint.publish("http://localhost:8080/UserEndpoint?wsdl",userEndPoint);
    }

    private void initUsers() {
        userService.persist("admin", PasswordHash.makehash("admin"), Role.ADMIN);
        userService.persist("user", PasswordHash.makehash("user"), Role.USER);

    }

}
