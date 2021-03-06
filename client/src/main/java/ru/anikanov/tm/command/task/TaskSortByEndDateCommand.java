package ru.anikanov.tm.command.task;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.TaskDto;
import ru.anikanov.tm.endpoint.TaskEndPoint;

import java.util.List;
import java.util.Objects;

public class TaskSortByEndDateCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "sortbyenddate project";
    }

    @Override
    public String getDescription() {
        return "return projects, sorted by end date";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        final TaskEndPoint endPoint = bootstrap.getTaskEndPoint();
        @Nullable final List<TaskDto> tasks = endPoint.sortTaskByFinishDate(bootstrap.getCurrentSession());
        Objects.requireNonNull(tasks).forEach(System.out::println);
    }
}
