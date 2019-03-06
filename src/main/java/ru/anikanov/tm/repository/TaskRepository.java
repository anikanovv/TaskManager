package ru.anikanov.tm.repository;

import ru.anikanov.tm.App;
import ru.anikanov.tm.entity.Task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskRepository {
    App app = new App();
    private Map<String, Task> taskMap = app.getTaskMap();

    public Task persist(String projectId, String taskName, String description, String dateStart, String dateFinish) throws ParseException {
        Task task = new Task(projectId, taskName, description, dateStart, dateFinish);
        if (!taskMap.containsValue(task)) {
            taskMap.put(task.getId(), task);
            return task;
        }
        return null;
    }

    public void merge(String taskName, String description, String dateStart, String dateFinish) throws ParseException {
        if (taskMap.containsKey(taskName)) {
            Task task = taskMap.get(taskName);
            task.setDescription(description);
            task.setStart(dateStart);
            task.setEnd(dateFinish);
        }
    }

    public Task remove(String taskName) {
        return taskMap.remove(taskName);
    }

    public void removeAll() {
        taskMap.clear();
    }

    public Task findOne(String taskName) {
        return taskMap.get(taskName);
    }

    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        taskMap.forEach((k, v) -> {
            tasks.add(v);
        });
        return tasks;
    }
}
