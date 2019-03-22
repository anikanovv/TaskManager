package ru.anikanov.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.service.ISessionService;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.endpoint.ProjectEndPoint;
import ru.anikanov.tm.endpoint.SessionEndPoint;
import ru.anikanov.tm.endpoint.TaskEndPoint;
import ru.anikanov.tm.endpoint.UserEndPoint;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.repository.ProjectRepository;
import ru.anikanov.tm.repository.SessionRepository;
import ru.anikanov.tm.repository.TaskRepository;
import ru.anikanov.tm.repository.UserRepository;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.SessionService;
import ru.anikanov.tm.service.TaskService;
import ru.anikanov.tm.service.UserService;
import ru.anikanov.tm.utils.PasswordHashUtil;

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
    @NotNull
    private final IProjectService projectService = new ProjectService(projectRepository, taskRepository, userRepository);
    @NotNull
    private final ITaskService taskService = new TaskService(projectRepository, taskRepository, userRepository);
    @NotNull
    private final IUserService userService = new UserService(userRepository);
    @NotNull
    private final ISessionService sessionService = new SessionService(sessionRepository);

    public void init() {
        initUsers();
        Endpoint.publish("http://localhost:8080/ProjectEndpoint?wsdl", new ProjectEndPoint(this));
        Endpoint.publish("http://localhost:8080/TaskEndpoint?wsdl", new TaskEndPoint(this));
        Endpoint.publish("http://localhost:8080/UserEndpoint?wsdl", new UserEndPoint(this));
        Endpoint.publish("http://localhost:8080/SessionEndpoint?wsdl", new SessionEndPoint(this));
    }

    private void initUsers() {
        User admin = userService.persist("admin", PasswordHashUtil.md5("admin"), Role.ADMIN);
        User user = userService.persist("user", PasswordHashUtil.md5("user"), Role.USER);
        Session userses = new Session();
        userses.setUserId(user.getId());
        Session adminses = new Session();
        adminses.setUserId(admin.getId());
        projectService.persist("new1", "descr1", "12.11.1234", "12.11.1234", userses);
        projectService.persist("new2", "descr2", "12.11.1234", "12.11.1234", adminses);
    }

}
