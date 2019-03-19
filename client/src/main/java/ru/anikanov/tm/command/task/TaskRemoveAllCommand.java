package ru.anikanov.tm.command.task;

import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.command.AbstractCommand;

public class TaskRemoveAllCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "removeall tasks";
    }

    @Override
    public String getDescription() {
        return "command to remove all tasks";
    }

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public void execute() {
        final ITaskService taskService = bootstrap.getTaskService();
        taskService.removeAll(bootstrap.getCurrentUser());
    }
}
