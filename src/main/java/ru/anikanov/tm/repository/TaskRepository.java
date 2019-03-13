package ru.anikanov.tm.repository;

import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.entity.AbstractEntity;
import ru.anikanov.tm.entity.Task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskRepository extends AbstractRepository implements ITaskRepository {
    private Map<String, Task> taskMap = new LinkedHashMap<>();

    public Task findOne(String taskName) {
        return taskMap.get(taskName);
    }

    public Task persist(Task entity) {
        Task task = entity;
        return taskMap.put(task.getId(), task);
    }

    public void merge(Task newtask) throws Exception {
        Task task = findOne(newtask.getTaskName());
        task.setDescription(newtask.getTaskDescription());
        task.setStart(newtask.getStartDate());
        task.setEnd(newtask.getEnd());
    }

    public void remove(String taskName) {
        taskMap.remove(taskName);
    }

    public void removeAll() {
        taskMap.clear();
    }

    public void removeWholeProject(String projectId) {
        taskMap.forEach((k, v) -> {
            if (k.equals(projectId)) taskMap.remove(k);
        });
    }

    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        taskMap.forEach((k, v) ->
                tasks.add(v)
        );
        return tasks;
    }
}
