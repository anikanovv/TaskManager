package ru.anikanov.tm.command.task;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.TaskServiceInterface;

public class TaskReadCommand extends AbstractCommand {

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public String getName() {
        return "read task";
    }

    @Override
    public String getDescription() {
        return "command to read task";
    }
    @Override
    public void execute() {
        TaskServiceInterface taskService = bootstrap.getTaskService();
        String name = scanner.nextLine();
        System.out.println(taskService.findOne(name, bootstrap.getCurrentUser()));
    }
}
