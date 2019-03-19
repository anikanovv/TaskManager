package ru.anikanov.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.api.service.ITerminalService;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.ProjectEndPointService;
import ru.anikanov.tm.endpoint.TaskEndPointService;
import ru.anikanov.tm.endpoint.UserEndPointService;

import java.util.Map;

public interface ServiceLocator {
    /*@NotNull
    ProjectEndPointService getProjectEndPointService();

    @NotNull
    TaskEndPointService getTaskEndPointService();

    @NotNull
    UserEndPointService getUserEndPointService();*/

    @NotNull
    IProjectService getProjectService();

    @NotNull
    ITaskService getTaskService();

    @NotNull
    Map<String, AbstractCommand> getCommandMap();

    @NotNull
    IUserService getUserService();

    @NotNull
    ITerminalService getTerminalService();

    @Nullable
    String getCurrentUser();

    void setCurrentUser(@NotNull final String name);

}
