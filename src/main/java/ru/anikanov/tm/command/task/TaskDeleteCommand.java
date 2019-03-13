package ru.anikanov.tm.command.task;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.command.AbstractCommand;

public class TaskDeleteCommand extends AbstractCommand {

    public TaskDeleteCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String getName() {
        return "delete task";
    }

    @Override
    public String getDescription() {
        return "command to delete task";
    }

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public void execute() {
        final ITaskService taskService = bootstrap.getTaskService();
        final String name = bootstrap.getTerminlService().nextLine();
        taskService.remove(name, bootstrap.getCurrentUser());
    }
}
