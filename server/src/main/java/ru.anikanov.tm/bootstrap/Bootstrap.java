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
import ru.anikanov.tm.entity.Project;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Objects;

@Getter
@Setter
public class Bootstrap implements ServiceLocator {
    @Nullable
    private Connection connection = ConnectionUtil.getConnection();
    @NotNull
    private ITaskRepository taskRepository = new TaskRepository();
    @NotNull
    private IProjectRepository projectRepository = new ProjectRepository(connection);
    @NotNull
    private IUserRepository userRepository = new UserRepository(connection);
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

    public static void main(String[] args) throws SQLException, ParseException {
        Bootstrap bootstrap=new Bootstrap();
        Connection conn= ConnectionUtil.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
//        bootstrap.getUserService().remove("user1","11");
//        bootstrap.getUserService().remove("user2","11");
//        User user = bootstrap.getUserService().persist("user2","Human","Looman","Looman@man.com", PasswordHashUtil.md5("user"), Role.USER);
//        User user1 = bootstrap.getUserService().persist("user1","Moman","Popan","Looman@man.com", PasswordHashUtil.md5("lol"), Role.USER);
//        Project project1=bootstrap.projectService.persist("123new123","ss","12.11.2011","12.11.2011",user1.getId());
//        Project project133=bootstrap.projectService.persist("newasdasdnew","ss","12.11.2011","12.11.2011",user.getId());
//        Project project12=bootstrap.projectService.persist("asdnnewasldhk","ss","12.11.2011","12.11.2011",user.getId());
        Project project = bootstrap.projectService.findOne("newasdasdnew", "f8da79db-0957-4b9d-baaa-f10c1e4088c9");
        if (project != null) System.out.println(project.getName());
//        System.out.println(bootstrap.projectService.findOne(project.getId(),user.getId()));

        try {
            stmt = conn.createStatement();
//                stmt.executeUpdate(query);
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}

