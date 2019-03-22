package ru.anikanov.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.endpoint.ProjectEndPoint;
import ru.anikanov.tm.endpoint.SessionEndPoint;
import ru.anikanov.tm.endpoint.TaskEndPoint;
import ru.anikanov.tm.endpoint.UserEndPoint;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.repository.ProjectRepository;
import ru.anikanov.tm.repository.SessionRepository;
import ru.anikanov.tm.repository.TaskRepository;
import ru.anikanov.tm.repository.UserRepository;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.SessionService;
import ru.anikanov.tm.service.TaskService;
import ru.anikanov.tm.service.UserService;
import ru.anikanov.tm.utils.PasswordHash;

import javax.xml.ws.Endpoint;

@Getter
@Setter
public class Bootstrap implements ServiceLocator {

    @NotNull
    private ITaskRepository taskRepository = new TaskRepository();
    @NotNull
    private IProjectRepository projectRepository = new ProjectRepository();
    @NotNull
    private IUserRepository userRepository = new UserRepository();
    @NotNull
    private SessionRepository sessionRepository = new SessionRepository();
    /* @NotNull
     private TaskEndPoint taskEndPoint = new TaskEndPoint(this);
     @NotNull
     private ProjectEndPoint projectEndPoint = new ProjectEndPoint(this);
     @NotNull
     private UserEndPoint userEndPoint = new UserEndPoint(this);
     @NotNull
     private SessionEndPoint sessionEndPoint = new SessionEndPoint(this);*/
    @NotNull
    private final IProjectService projectService = new ProjectService(projectRepository, taskRepository, userRepository);
    @NotNull
    private final ITaskService taskService = new TaskService(projectRepository, taskRepository, userRepository);
    @NotNull
    private final IUserService userService = new UserService(userRepository);
    private final SessionService sessionService = new SessionService(sessionRepository);

    public void init() {
        initUsers();
        Endpoint.publish("http://localhost:8080/ProjectEndpoint?wsdl", new ProjectEndPoint(this));
        Endpoint.publish("http://localhost:8080/TaskEndpoint?wsdl", new TaskEndPoint(this));
        Endpoint.publish("http://localhost:8080/UserEndpoint?wsdl", new UserEndPoint(this));
        Endpoint.publish("http://localhost:8080/SessionEndpoint?wsdl", new SessionEndPoint(this));
    }

    private void initUsers() {
        userService.persist("admin", PasswordHash.makehash("admin"), Role.ADMIN);
        userService.persist("user", PasswordHash.makehash("user"), Role.USER);

    }

}
