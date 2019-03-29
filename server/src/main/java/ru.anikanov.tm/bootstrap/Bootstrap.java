package ru.anikanov.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.api.service.*;
import ru.anikanov.tm.endpoint.*;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.repository.ProjectRepository;
import ru.anikanov.tm.repository.SessionRepository;
import ru.anikanov.tm.repository.TaskRepository;
import ru.anikanov.tm.repository.UserRepository;
import ru.anikanov.tm.service.*;
import ru.anikanov.tm.utils.ConnectionUtil;
import ru.anikanov.tm.utils.PasswordHashUtil;

import javax.xml.ws.Endpoint;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

@Getter
@Setter
public class Bootstrap implements ServiceLocator {
    @Nullable
    private Connection connection = ConnectionUtil.getConnection();
    @NotNull
    private ITaskRepository taskRepository = new TaskRepository(connection);
    @NotNull
    private IProjectRepository projectRepository = new ProjectRepository(connection);
    @NotNull
    private IUserRepository userRepository = new UserRepository(connection);
    @NotNull
    private SessionRepository sessionRepository = new SessionRepository(connection);
    @NotNull
    private final IProjectService projectService = new ProjectService(projectRepository, taskRepository, userRepository);
    @NotNull
    private final ITaskService taskService = new TaskService(projectRepository, taskRepository, userRepository);
    @NotNull
    private final IUserService userService = new UserService(userRepository);
    @NotNull
    private final ISessionService sessionService = new SessionService(sessionRepository);
    @NotNull
    private final IDomainService domainService = new DomainService(this);

    public Bootstrap() throws SQLException {
    }

    public void init() {
        initUsers();
        Endpoint.publish("http://localhost:8080/ProjectEndpoint?wsdl", new ProjectEndPoint(this));
        Endpoint.publish("http://localhost:8080/TaskEndpoint?wsdl", new TaskEndPoint(this));
        Endpoint.publish("http://localhost:8080/UserEndpoint?wsdl", new UserEndPoint(this));
        Endpoint.publish("http://localhost:8080/SessionEndpoint?wsdl", new SessionEndPoint(this));
        Endpoint.publish("http://localhost:8080/DomainEndpoint?wsdl", new DomainEndPoint(this));
    }

    private void initUsers() {
        User admin = userService.persist("admin", "Man", "Man", "Man@man.com", PasswordHashUtil.md5("admin"), Role.ADMIN);
        User user = userService.persist("user", "Human", "Looman", "Looman@man.com", PasswordHashUtil.md5("user"), Role.USER);
        Session userses = new Session();
        userses.setUserId(Objects.requireNonNull(user).getId());
        Session adminses = new Session();
        adminses.setUserId(Objects.requireNonNull(admin).getId());
        projectService.persist("new1", "descr1", "12.11.1234", "12.11.1234", Objects.requireNonNull(userses.getUserId()));
        projectService.persist("new2", "descr2", "12.11.1234", "12.11.1234", Objects.requireNonNull(adminses.getUserId()));
    }
/*    public static void main(String[] args) throws SQLException{
        Bootstrap bootstrap = new Bootstrap();
        User user = bootstrap.getUserService().findOne("uuu","11bd6325-0373-42fb-9474-3ae474deb79a");
        Session adminses=bootstrap.sessionService.create(user.getId());
        System.out.println(bootstrap.taskService.findByPartOfName("name2","11bd6325-0373-42fb-9474-3ae474deb79a"));
       if (bootstrap.sessionService.validate(adminses)) System.out.println("TURE");
    }*/
}

