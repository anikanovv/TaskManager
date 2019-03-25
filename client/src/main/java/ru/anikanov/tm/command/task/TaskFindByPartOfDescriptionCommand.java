package ru.anikanov.tm.command.task;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.Task;
import ru.anikanov.tm.endpoint.TaskEndPoint;

public class TaskFindByPartOfDescriptionCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "findbydescription task";
    }

    @Override
    public String getDescription() {
        return "find task by part of description";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        final TaskEndPoint endPoint= bootstrap.getTaskEndPoint();
        System.out.println("type description(part of description)");
        @Nullable final String partOfDescription = bootstrap.getTerminalService().nextLine();

        @Nullable Task task = endPoint.findTaskByPartOfDescription(bootstrap.getCurrentSession(), partOfDescription);
        System.out.println(task);
    }
}