package ru.anikanov.tm.service;

import ru.anikanov.tm.entity.Task;
import ru.anikanov.tm.repository.TaskRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    TaskRepository taskRepository = new TaskRepository();

    public Task findOne(String taskName) {
        if (taskName.isEmpty() || (taskName == null)) return null;
        return taskRepository.findOne(taskName);
    }

    public Task persist(String projectId, String taskName, String description, String dateStart, String dateFinish) throws ParseException {
        if (taskName.isEmpty() || (taskName == null)) return null;
        Task task = taskRepository.findOne(taskName);
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

    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        taskMap.forEach((k, v) -> {
            tasks.add(v);
        });
        return tasks;
    }
}