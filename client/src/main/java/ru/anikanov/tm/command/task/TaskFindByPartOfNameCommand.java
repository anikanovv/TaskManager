package ru.anikanov.tm.command.task;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.TaskDto;
import ru.anikanov.tm.endpoint.TaskEndPoint;

public class TaskFindByPartOfNameCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "findbypart task";
    }

    @Override
    public String getDescription() {
        return "find task by part of name";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        final TaskEndPoint endPoint = bootstrap.getTaskEndPoint();
        System.out.println("type name(part of name)");
        @Nullable final String partOfName = bootstrap.getTerminalService().nextLine();
        @Nullable TaskDto task = endPoint.findTaskByPartOfName(bootstrap.getCurrentSession(), partOfName);
        System.out.println(task);
    }
}
