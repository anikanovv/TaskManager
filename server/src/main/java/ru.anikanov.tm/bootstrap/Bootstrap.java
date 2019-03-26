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
import java.util.Objects;

@Getter
@Setter
public class Bootstrap implements ServiceLocator {
    @Nullable
    private Connection connection = ConnectionUtil.getConnection();
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
    @NotNull
    private final IDomainService domainService = new DomainService(this);

    public void init() {
        initUsers();
        Endpoint.publish("http://localhost:8080/ProjectEndpoint?wsdl", new ProjectEndPoint(this));
        Endpoint.publish("http://localhost:8080/TaskEndpoint?wsdl", new TaskEndPoint(this));
        Endpoint.publish("http://localhost:8080/UserEndpoint?wsdl", new UserEndPoint(this));
        Endpoint.publish("http://localhost:8080/SessionEndpoint?wsdl", new SessionEndPoint(this));
        Endpoint.publish("http://localhost:8080/DomainEndpoint?wsdl", new DomainEndPoint(this));
    }

    private void initUsers() {
        User admin = userService.persist("admin", PasswordHashUtil.md5("admin"), Role.ADMIN);
        User user = userService.persist("user", PasswordHashUtil.md5("user"), Role.USER);
        Session userses = new Session();
        userses.setUserId(Objects.requireNonNull(user).getId());
        Session adminses = new Session();
        adminses.setUserId(Objects.requireNonNull(admin).getId());
        projectService.persist("new1", "descr1", "12.11.1234", "12.11.1234", Objects.requireNonNull(userses.getUserId()));
        projectService.persist("new2", "descr2", "12.11.1234", "12.11.1234", Objects.requireNonNull(adminses.getUserId()));
    }
/*
    public static void main(String[] args) {
        Bootstrap bootstrap=new Bootstrap();
        Connection conn= ConnectionUtil.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Project project= Objects.requireNonNull(bootstrap.projectService.persist("new1", "descr1", "12.11.1234", "12.11.1234", null));
        try {
            stmt = conn.createStatement();
            String query = "INSERT INTO test.books (id, name, author) \n" +
                    " VALUES (3, 'Head First Java', 'Kathy Sieara');";
                stmt.executeUpdate(query);
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }*/


}
