package ru.anikanov.tm.command.task;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.TaskEndPoint;

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
        final TaskEndPoint endPoint= bootstrap.getTaskEndPoint();
        final String name = bootstrap.getTerminalService().nextLine();
        System.out.println(endPoint.findTaskByPartOfName(name, bootstrap.getCurrentUser()));
    }
}
