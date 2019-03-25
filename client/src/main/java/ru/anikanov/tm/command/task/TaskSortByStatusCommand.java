package ru.anikanov.tm.command.task;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.Task;
import ru.anikanov.tm.endpoint.TaskEndPoint;

import java.util.List;

public class TaskSortByStatusCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "sortbystatus project";
    }

    @Override
    public String getDescription() {
        return "return projects, sorted by status";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        final TaskEndPoint endPoint= bootstrap.getTaskEndPoint();
        @Nullable final List<Task> tasks = endPoint.sortTaskByStatus(bootstrap.getCurrentSession());
        if (tasks != null) {
            tasks.forEach(System.out::println);
        }
    }
}
