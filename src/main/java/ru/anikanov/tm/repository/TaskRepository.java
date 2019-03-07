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

    public Task persist(String projectId, String taskName, String description, String dateStart, String dateFinish) throws ParseException {
        Task task = new Task(projectId, taskName, description, dateStart, dateFinish);
        if (!taskMap.containsValue(task)) {
            taskMap.put(task.getId(), task);
            return task;
        }
        return null;
    }

    public void merge(String taskName, String description, String dateStart, String dateFinish) throws ParseException {
            Task task = taskMap.get(taskName);
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

    public Task findOne(String taskName) {
        return taskMap.get(taskName);
    }

    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        taskMap.forEach((k, v) ->
                tasks.add(v)
        );
        return tasks;
    }
}
