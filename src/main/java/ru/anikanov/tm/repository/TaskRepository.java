package ru.anikanov.tm.repository;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.entity.Task;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class TaskRepository extends AbstractRepository implements ITaskRepository {

    @NotNull
    private Map<String, Task> taskMap = new LinkedHashMap<>();

    @Nullable
    public Task findOne(@NotNull final String taskName) {
        return taskMap.get(taskName);
    }

    @NotNull
    public Task persist(@NotNull final Task entity) {
        return taskMap.put(entity.getId(), entity);
    }

    public void merge(@NotNull final Task newtask) throws Exception {
        @Nullable Task task = findOne(newtask.getTaskName());
        task.setTaskDescription(newtask.getTaskDescription());
        task.setStart(newtask.getStartDate());
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
        List<Task> tasks = new ArrayList<>();
        taskMap.forEach((k, v) ->
                tasks.add(v)
        );
        return tasks;
    }
}
