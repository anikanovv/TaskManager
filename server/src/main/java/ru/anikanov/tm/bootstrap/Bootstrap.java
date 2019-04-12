package ru.anikanov.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.endpoint.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.ws.Endpoint;

@Getter
@Setter
@ApplicationScoped
public class Bootstrap implements ServiceLocator {
    @Inject
    @NotNull
    private ProjectEndPoint projectEndPoint;
    @Inject
    @NotNull
    private TaskEndPoint taskEndPoint;
    @Inject
    @NotNull
    private UserEndPoint userEndPoint;
    @Inject
    @NotNull
    private SessionEndPoint sessionEndPoint;
    @Inject
    @NotNull
    private DomainEndPoint domainEndPoint;

    public Bootstrap() {
    }

    public void init() {
        Endpoint.publish("http://localhost:8080/ProjectEndpoint?wsdl", projectEndPoint);
        Endpoint.publish("http://localhost:8080/TaskEndpoint?wsdl", taskEndPoint);
        Endpoint.publish("http://localhost:8080/UserEndpoint?wsdl", userEndPoint);
        Endpoint.publish("http://localhost:8080/SessionEndpoint?wsdl", sessionEndPoint);
        Endpoint.publish("http://localhost:8080/DomainEndpoint?wsdl", domainEndPoint);
    }
}

