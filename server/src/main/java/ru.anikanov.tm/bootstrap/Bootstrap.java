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
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.repository.*;
import ru.anikanov.tm.service.*;
import ru.anikanov.tm.utils.ConnectionUtil;
import ru.anikanov.tm.utils.PasswordHashUtil;
import ru.anikanov.tm.utils.SignatureUtil;
import ru.anikanov.tm.utils.SqlSessionFactory;

import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

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
        @Nullable final User admin = userService.persist("admin", "Man", "Man", "Man@man.com", PasswordHashUtil.md5("admin"), Role.ADMIN);
        @Nullable final User user = userService.persist("user", "Human", "Looman", "Looman@man.com", PasswordHashUtil.md5("user"), Role.USER);
//        projectService.persist("new1", "descr1", "12.11.1234", "12.11.1234", admin.getId());
//        projectService.persist("new2", "descr2", "12.11.1234", "12.11.1234", user.getId());
    }

    public static void main(String[] args) throws SQLException, IOException {
        Bootstrap bootstrap = new Bootstrap();
        SqlSession sqlSession = new SqlSessionFactory().getSqlSessionFactory().openSession();
        Session session = new Session("11bd6325-0373-42fb-9474-3ae474deb79a", System.currentTimeMillis());
        session.setId(UUID.randomUUID().toString());
        session.setSignature(SignatureUtil.sign(session, "salt", 22));
//        session.getConfiguration().addMapper(SessionMapper.class);
        SessionMapper mapper = sqlSession.getMapper(SessionMapper.class);
        TaskMapper mapper1 = sqlSession.getMapper(TaskMapper.class);
        ProjectMapper mapper2 = sqlSession.getMapper(ProjectMapper.class);
        UserMapper mapper3 = sqlSession.getMapper(UserMapper.class);
        mapper.create(session);
        Session session1 = mapper.findOne("cffbdaf1-4737-4099-8bd2-ed0847aa1902");
        List<Project> projects = mapper2.findAll("e742c623-e978-4b2b-8439-fd1ec41cebf7");
//        Task task = mapper1.findByPartOfDescription("NEWONEEEEE", "11bd6325-0373-42fb-9474-3ae474deb79a");
//        task.setId("11bd6325-0773-42fb-9474-3ae474deb79a");
        mapper1.merge("11bd6325-0773-42fb-9474-3ae474deb79a", "SSS", "SWSD", "12.05.2000", "13.08.2011", "11bd6325-0373-42fb-9474-3ae474deb79a");
        List<User> users = mapper3.findAll();
        sqlSession.commit();
        sqlSession.close();
    }
}

