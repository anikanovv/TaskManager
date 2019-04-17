package ru.anikanov.tm.bootstrap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.endpoint.*;

import javax.xml.ws.Endpoint;

@Getter
@Setter
@Component
@NoArgsConstructor
public class Bootstrap implements ServiceLocator {
    @Autowired
    @NotNull
    private ProjectEndPoint projectEndPoint;
    @Autowired
    @NotNull
    private TaskEndPoint taskEndPoint;
    @Autowired
    @NotNull
    private UserEndPoint userEndPoint;
    @Autowired
    @NotNull
    private SessionEndPoint sessionEndPoint;
    @Autowired
    @NotNull
    private DomainEndPoint domainEndPoint;

    public void init() {
        Endpoint.publish("http://localhost:8080/ProjectEndpoint?wsdl", projectEndPoint);
        Endpoint.publish("http://localhost:8080/TaskEndpoint?wsdl", taskEndPoint);
        Endpoint.publish("http://localhost:8080/UserEndpoint?wsdl", userEndPoint);
        Endpoint.publish("http://localhost:8080/SessionEndpoint?wsdl", sessionEndPoint);
        Endpoint.publish("http://localhost:8080/DomainEndpoint?wsdl", domainEndPoint);
    }
}

