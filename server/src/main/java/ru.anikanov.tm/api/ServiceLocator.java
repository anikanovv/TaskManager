package ru.anikanov.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.api.service.*;

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


   /* @Nullable
    String getCurrentSession();

    void setCurrentSession(@NotNull final Session name);*/

}
