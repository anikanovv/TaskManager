package ru.anikanov.tm.command.task;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;
import ru.anikanov.tm.service.TaskServiceInterface;

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
        TaskServiceInterface taskService = bootstrap.getTaskService();
        String name = scanner.nextLine();
        taskService.remove(name, bootstrap.getCurrentUser());
    }
}
