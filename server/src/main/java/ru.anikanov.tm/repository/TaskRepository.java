package ru.anikanov.tm.repository;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.entity.Task;

import java.util.*;

@Getter
public class TaskRepository implements ITaskRepository {

    @NotNull
    private Map<String, Task> taskMap = new LinkedHashMap<>();

    @Nullable
    public Task findOne(@NotNull final String taskName) {
        return taskMap.get(taskName);
    }

    @Nullable
    public Task persist(@NotNull final Task entity) {
        return taskMap.put(entity.getId(), entity);
    }

    public Task merge(@Nullable final String taskName, @Nullable final String description,
                      @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final String userId) {
        if (taskName == null) return null;
        @Nullable final Task task = findOne(taskName);
        if (task == null) return null;
        task.setTaskDescription(description);
        task.setStart(dateStart);
        task.setEnd(dateFinish);
        task.setUserId(userId);
        return task;
    }

    public void remove(@NotNull final String taskName) {
        taskMap.remove(taskName);
    }

    public void removeAll() {
        taskMap.clear();
    }

    public void removeWholeProject(@NotNull final String projectId) {
        taskMap.forEach((k, v) -> {
            if (k.equals(projectId)) taskMap.remove(k);
        });
    }

    @Nullable
    public List<Task> findAll(@NotNull final String userId) {
        @Nullable List<Task> tasks = new ArrayList<>();
        taskMap.forEach((k, v) -> {
            if (v.getUserId().equals(userId))
                tasks.add(v);
        });
        return tasks;
    }

    @Nullable
    public List<Task> sortedByStartDate(@NotNull final String userId) {
        List<Task> tasks = findAll(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getStartDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByFinishDate(@NotNull final String userId) {
        @Nullable List<Task> tasks = findAll(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getEndDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByStatus(@NotNull final String userId) {
        @Nullable List<Task> tasks = findAll(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getStatus));
        return tasks;
    }

    @Nullable
    public Task findByPartOfName(@NotNull final String partOfName, @NotNull final String userId) {
        @Nullable final List<Task> tasks = findAll(userId);
        @Nullable Task thistask = null;
        if (tasks == null) return null;
        for (Task task : tasks) {
            if ((task.getTaskName() != null) && task.getTaskName().contains(partOfName)) thistask = task;
        }
        return thistask;
    }

    @Nullable
    public Task findByPartOfDescription(@NotNull final String partOfDescription, @NotNull final String userId) {
        @Nullable final List<Task> tasks = findAll(userId);
        @Nullable Task thistask = null;
        if (tasks == null) return null;
        for (Task task : tasks) {
            if ((task.getTaskDescription() != null) && task.getTaskDescription().contains(partOfDescription))
                thistask = task;
        }
        return thistask;
    }
}
