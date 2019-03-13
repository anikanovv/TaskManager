package ru.anikanov.tm.api;

import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.api.service.ITermincalService;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
import java.util.Map;
import java.util.Scanner;

public interface ServiceLocator {

    IProjectService getProjectService();

    ITaskService getTaskService();

    Map<String, AbstractCommand> getCommandMap();

    IUserService getUserService();

    ITermincalService getTerminlService();

    String getCurrentUser();

    void setCurrentUser(String name);

}
