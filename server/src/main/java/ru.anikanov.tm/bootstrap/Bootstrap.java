package ru.anikanov.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.endpoint.*;

import javax.inject.Inject;
import javax.xml.ws.Endpoint;

@Getter
@Setter
public class Bootstrap implements ServiceLocator {
    @NotNull
    @Inject
    private ProjectEndPoint projectEndPoint;
    @Inject
    @NotNull
    private TaskEndPoint taskEndPoint;
    @NotNull
    @Inject
    private UserEndPoint userEndPoint;
    @Inject
    @NotNull
    private SessionEndPoint sessionEndPoint;
    @NotNull
    @Inject
    private DomainEndPoint domainEndPoint;

    public Bootstrap() throws Exception {
    }

    public void init() {
        Endpoint.publish("http://localhost:8080/ProjectEndpoint?wsdl", new ProjectEndPoint());
        Endpoint.publish("http://localhost:8080/TaskEndpoint?wsdl", new TaskEndPoint());
        Endpoint.publish("http://localhost:8080/UserEndpoint?wsdl", new UserEndPoint());
        Endpoint.publish("http://localhost:8080/SessionEndpoint?wsdl", new SessionEndPoint());
        Endpoint.publish("http://localhost:8080/DomainEndpoint?wsdl", new DomainEndPoint());
    }
}

