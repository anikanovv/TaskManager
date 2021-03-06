package ru.anikanov.tm.command.task;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.TaskDto;
import ru.anikanov.tm.endpoint.TaskEndPoint;

import java.util.List;

public class TaskSortByStartDateCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "sortbystartdate tasks";
    }

    @Override
    public String getDescription() {
        return "return tasks, sorted by start date";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        final TaskEndPoint endPoint = bootstrap.getTaskEndPoint();
        @Nullable final List<TaskDto> tasks = endPoint.sortTaskByStartDate(bootstrap.getCurrentSession());
        assert tasks != null;
        tasks.forEach(System.out::println);
    }
}