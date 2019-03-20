package ru.anikanov.tm.command.task;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.TaskEndPoint;

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
        final TaskEndPoint endPoint= bootstrap.getTaskEndPoint();
        endPoint.removeAllTask(bootstrap.getCurrentUser());
    }
}
