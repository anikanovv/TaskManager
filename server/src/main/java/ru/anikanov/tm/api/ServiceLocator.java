package ru.anikanov.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.endpoint.*;

public interface ServiceLocator {

    @NotNull
    ProjectEndPoint getProjectEndPoint();

    @NotNull
    TaskEndPoint getTaskEndPoint();

    @NotNull
    UserEndPoint getUserEndPoint();

    @NotNull
    SessionEndPoint getSessionEndPoint();

    @NotNull
    DomainEndPoint getDomainEndPoint();

    void init();

}
