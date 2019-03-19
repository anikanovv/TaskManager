package ru.anikanov.tm.command.task;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Task;

import java.util.List;

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
        final ITaskService taskService = bootstrap.getTaskService();
        @Nullable final List<Task> tasks = taskService.sortedByFinishDate(bootstrap.getCurrentUser());
        tasks.forEach(task -> System.out.println(task));
    }
}
