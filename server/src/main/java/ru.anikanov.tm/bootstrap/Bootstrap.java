package ru.anikanov.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.*;
import ru.anikanov.tm.endpoint.*;
import ru.anikanov.tm.entity.Task;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.service.*;
import ru.anikanov.tm.utils.EMFactory;
import ru.anikanov.tm.utils.PasswordHashUtil;

import javax.persistence.EntityManagerFactory;
import javax.xml.ws.Endpoint;

@Getter
@Setter
public class Bootstrap implements ServiceLocator {
    @NotNull
    private EntityManagerFactory factory = new EMFactory().factory();
    @NotNull
    private final IProjectService projectService = new ProjectService(factory);
    @NotNull
    private final ITaskService taskService = new TaskService(factory);
    @NotNull
    private final IUserService userService = new UserService(factory);
    @NotNull
    private final ISessionService sessionService = new SessionService(factory);
    @NotNull
    private final IDomainService domainService = new DomainService(this);

    public Bootstrap() throws Exception {
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

    public static void main(String[] args) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        Task project = bootstrap.taskService.findOne("2faa3268-cc28-428e-bcfe-cee8584ad7de", "e742c623-e978-4b2b-8439-fd1ec41cebf7");
        //        Session session=bootstrap.sessionService.findOne("bb290dc7-4c51-4128-98f1-70a374bfd921");
//        List<User>users=bootstrap.userService.findAll();
//        bootstrap.userService.merge("Cheburek", "Kek", "Looman", "Looman@man.com", PasswordHashUtil.md5("user"), Role.USER,user.getId());
        System.out.println("11");
    }
}

