package ru.anikanov.tm.service;

import ru.anikanov.tm.entity.Task;
import ru.anikanov.tm.repository.ProjectRepository;
import ru.anikanov.tm.repository.TaskRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    public TaskService(ProjectRepository pr, TaskRepository tr) {
        projectRepository = pr;
        taskRepository = tr;
    }

    public Task findOne(String taskId) {
        if (taskId.isEmpty() || (taskId == null)) return null;
        return taskRepository.findOne(taskId);
    }

    public Task persist(String projectId, String taskName, String description, String dateStart, String dateFinish) throws ParseException {
        if (taskName.isEmpty() || (taskName == null)) return null;
        Task task = taskRepository.findOne(taskName);
        if (task == null) {
            if (projectId.isEmpty() || (projectId == null)) return null;
            if (description.isEmpty() || (description == null)) return null;
            if (dateStart.isEmpty() || (dateStart == null)) return null;
            if (dateFinish.isEmpty() || (dateFinish == null)) return null;
        }
        return taskRepository.persist(projectId, taskName, description, dateStart, dateFinish);
    }

    public void merge(String taskId, String taskName, String description, String dateStart, String dateFinish) throws ParseException {
        if (taskId.isEmpty() || (taskId == null)) return;
        Task task = taskRepository.findOne(taskId);
        if (task != null) {
            if (taskName.isEmpty() || (taskName == null)) return;
            if (description.isEmpty() || (description == null)) return;
            if (dateStart.isEmpty() || (dateStart == null)) return;
            if (dateFinish.isEmpty() || (dateFinish == null)) return;
        }
        taskRepository.merge(taskId, taskName, description, dateStart, dateFinish);

    }

    public Task remove(String taskId) {
        if (taskId.isEmpty() || (taskId == null)) return null;
        return taskRepository.remove(taskId);
    }

    public void removeAll() {
        taskRepository.removeAll();
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
