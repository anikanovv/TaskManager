package ru.anikanov.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.ITerminalService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.*;

import java.util.Map;

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
    Map<String, AbstractCommand> getCommandMap();

    @NotNull
    ITerminalService getTerminalService();

    @Nullable
    String getCurrentUser();

    void setCurrentUser(@NotNull final String name);

    @Nullable
    Session getCurrentSession();

    void setCurrentSession(@NotNull final Session session);

}
