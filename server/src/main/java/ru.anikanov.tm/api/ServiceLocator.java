package ru.anikanov.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.service.ISessionService;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.api.service.IUserService;

public interface ServiceLocator {

    @NotNull
    IProjectService getProjectService();

    @NotNull
    ITaskService getTaskService();

    @NotNull
    IUserService getUserService();

    @NotNull
    ISessionService getSessionService();

   /* @Nullable
    String getCurrentSession();

    void setCurrentSession(@NotNull final Session name);*/

}
