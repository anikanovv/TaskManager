package ru.anikanov.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.api.service.ITerminalService;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
import java.util.Map;

public interface ServiceLocator {

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
