package ru.anikanov.tm.command.task;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.TaskEndPoint;

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
        final TaskEndPoint endPoint= bootstrap.getTaskEndPoint();
        final String name = bootstrap.getTerminalService().nextLine();
        endPoint.removeTask(name, bootstrap.getCurrentUser());
    }
}
