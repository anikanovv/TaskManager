package ru.anikanov.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.api.service.*;

import java.sql.Connection;

public interface ServiceLocator {

    @NotNull
    IProjectService getProjectService();

    @NotNull
    ITaskService getTaskService();

    @NotNull
    IUserService getUserService();

    @NotNull
    ISessionService getSessionService();

    @NotNull
    IDomainService getDomainService();

    @NotNull
    Connection getConnection();

   /* @Nullable
    String getCurrentSession();

    void setCurrentSession(@NotNull final Session name);*/

}
