package ru.anikanov.tm.repository;

import ru.anikanov.tm.App;
import ru.anikanov.tm.entity.Task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskRepository {
    private Map<String, Task> taskMap = new LinkedHashMap<>();

    public Task findOne(String taskName) {
        return taskMap.get(taskName);
    }

    public Task persist(String projectId, String taskName, String description, String dateStart, String dateFinish, String userId) throws ParseException {
        Task task = new Task(projectId, taskName, description, dateStart, dateFinish, userId);
        return taskMap.put(task.getId(), task);
    }

    public void merge(String taskId, String taskName, String description, String dateStart, String dateFinish) throws ParseException {
        Task task = taskMap.get(taskId);
        task.setTaskName(taskName);
            task.setDescription(description);
            task.setStart(dateStart);
            task.setEnd(dateFinish);
    }

    public Task remove(String taskName) {
        return taskMap.remove(taskName);
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
