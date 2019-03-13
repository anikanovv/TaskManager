package ru.anikanov.tm.command.task;

import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.command.AbstractCommand;

public class TaskDeleteCommand extends AbstractCommand {

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
        ITaskService taskService = bootstrap.getTaskService();
        String name = scanner.nextLine();
        taskService.remove(name, bootstrap.getCurrentUser());
    }
}
