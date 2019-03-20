package ru.anikanov.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.api.service.IUserService;

public interface ServiceLocator {

    @NotNull
    IProjectService getProjectService();

    @NotNull
    ITaskService getTaskService();

    @NotNull
    IUserService getUserService();

    @Nullable
    String getCurrentUser();

    void setCurrentUser(@NotNull final String name);

}
