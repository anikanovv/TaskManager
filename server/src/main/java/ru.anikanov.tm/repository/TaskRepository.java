package ru.anikanov.tm.repository;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.entity.Task;

import java.util.*;

@Getter
public class TaskRepository extends AbstractRepository implements ITaskRepository {

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

    public void merge(@NotNull final Task newtask) throws Exception {
        if (newtask.getTaskName() == null) throw new Exception();
        @Nullable final Task task = findOne(newtask.getTaskName());
        if (task == null) throw new Exception();
        task.setTaskDescription(newtask.getTaskDescription());
        task.setStart(newtask.getStart());
        task.setEnd(newtask.getEnd());
    }

    public void remove(@NotNull final String taskName) {
        taskMap.remove(taskName);
    }

    public void removeAll() {
        taskMap.clear();
    }

    public void removeWholeProject(@NotNull String projectId) {
        taskMap.forEach((k, v) -> {
            if (k.equals(projectId)) taskMap.remove(k);
        });
    }

    @Nullable
    public List<Task> findAll() {
        @Nullable List<Task> tasks = new ArrayList<>();
        taskMap.forEach((k, v) ->
                tasks.add(v)
        );
        return tasks;
    }

    @Nullable
    public List<Task> sortedByStartDate() throws Exception {
        List<Task> tasks = findAll();
        if (tasks == null) throw new Exception();
        tasks.sort(Comparator.comparing(Task::getStartDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByFinishDate() throws Exception {
        @Nullable List<Task> tasks = findAll();
        if (tasks == null) throw new Exception();
        tasks.sort(Comparator.comparing(Task::getEndDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByStatus() throws Exception {
        @Nullable List<Task> tasks = findAll();
        if (tasks == null) throw new Exception();
        tasks.sort(Comparator.comparing(Task::getStatus));
        return tasks;
    }

    @Nullable
    public Task findByPartOfName(@NotNull String partOfName) throws Exception {
        @Nullable final List<Task> tasks = findAll();
        @Nullable Task thistask = null;
        if (tasks == null) throw new Exception();
        for (Task task : tasks) {
            if ((task.getTaskName() != null) && task.getTaskName().contains(partOfName)) thistask = task;
        }
        return thistask;
    }

    @Nullable
    public Task findByPartOfDescription(@NotNull String partOfDescription) throws Exception {
        @Nullable final List<Task> tasks = findAll();
        @Nullable Task thistask = null;
        if (tasks == null) throw new Exception();
        for (Task task : tasks) {
            if ((task.getTaskDescription() != null) && task.getTaskDescription().contains(partOfDescription))
                thistask = task;
        }
        return thistask;
    }
}
