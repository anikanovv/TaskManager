package ru.anikanov.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.api.service.*;
import ru.anikanov.tm.endpoint.*;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.repository.*;
import ru.anikanov.tm.service.*;
import ru.anikanov.tm.utils.ConnectionUtil;
import ru.anikanov.tm.utils.PasswordHashUtil;
import ru.anikanov.tm.utils.SqlSessionFactory;

import javax.xml.ws.Endpoint;
import java.sql.Connection;
import java.util.List;

@Getter
@Setter
public class Bootstrap implements ServiceLocator {
    @Nullable
    private Connection connection = ConnectionUtil.getConnection();
    @NotNull
    private SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
    @NotNull
    private ITaskRepository taskRepository = new TaskRepository(connection);
    @NotNull
    private IProjectRepository projectRepository = new ProjectRepository(connection);
    @NotNull
    private IUserRepository userRepository = new UserRepository(connection);
    @NotNull
    private SessionRepository sessionRepository = new SessionRepository(connection);
    @NotNull
    private ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
    @NotNull
    private TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);
    @NotNull
    private UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    @NotNull
    private SessionMapper sessionMapper = sqlSession.getMapper(SessionMapper.class);
    @NotNull
    private final IProjectService projectService = new ProjectService();
    @NotNull
    private final ITaskService taskService = new TaskService();
    @NotNull
    private final IUserService userService = new UserService();
    @NotNull
    private final ISessionService sessionService = new SessionService();
    @NotNull
    private final IDomainService domainService = new DomainService(this);

    public Bootstrap() {
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
        @Nullable final User admin = userService.persist("admin", "Man", "Man", "Man@man.com", PasswordHashUtil.md5("admin"), Role.ADMIN);
        @Nullable final User user = userService.persist("user", "Human", "Looman", "Looman@man.com", PasswordHashUtil.md5("user"), Role.USER);
//        projectService.persist("new1", "descr1", "12.11.1234", "12.11.1234", admin.getId());
//        projectService.persist("new2", "descr2", "12.11.1234", "12.11.1234", user.getId());
    }

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        User user = bootstrap.userService.findByName("57a91905-57c5-4774-a9c6-1411b024c003");
        List<Project> projects = bootstrap.projectService.findAll(user.getId());
        user = bootstrap.userService.findByName("57a91905-57c5-4774-a9c6-1411b024c003");

    }
}

