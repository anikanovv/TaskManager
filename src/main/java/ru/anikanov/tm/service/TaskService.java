package ru.anikanov.tm.service;

import ru.anikanov.tm.entity.Task;
import ru.anikanov.tm.repository.ProjectRepository;
import ru.anikanov.tm.repository.TaskRepository;
import ru.anikanov.tm.repository.UserRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskService implements TaskServiceInterface {
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public TaskService(ProjectRepository pr, TaskRepository tr, UserRepository ur) {
        projectRepository = pr;
        taskRepository = tr;
        userRepository = ur;
    }

    public Task findOne(String taskId, String userId) {
        if (taskId.isEmpty() || (taskId == null)) return null;
        return taskRepository.findOne(taskId);
    }

    public Task persist(String projectId, String taskName, String description, String dateStart, String dateFinish, String userId) {
        if (taskName.isEmpty() || (taskName == null)) return null;
        Task task = taskRepository.findOne(taskName);
        if (task == null) {
            if (projectId.isEmpty() || (projectId == null)) return null;
            if (description.isEmpty() || (description == null)) return null;
            if (dateStart.isEmpty() || (dateStart == null)) return null;
            if (dateFinish.isEmpty() || (dateFinish == null)) return null;
            if (userId.isEmpty() || (userId == null)) return null;
        }
        try {
            return taskRepository.persist(projectId, taskName, description, dateStart, dateFinish, userId);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void merge(String taskId, String taskName, String description, String dateStart, String dateFinish, String userId) {
        if (taskId.isEmpty() || (taskId == null)) return;
        Task task = taskRepository.findOne(taskId);
        if ((!userId.equals(task.getUserId())) || (!userRepository.findOne(userId).getRole().equals("admin"))) return;
        if (task != null) {
            if (taskName.isEmpty() || (taskName == null)) return;
            if (description.isEmpty() || (description == null)) return;
            if (dateStart.isEmpty() || (dateStart == null)) return;
            if (dateFinish.isEmpty() || (dateFinish == null)) return;
        }
        try {
            taskRepository.merge(taskId, taskName, description, dateStart, dateFinish);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public Task remove(String taskId, String userId) {
        if (taskId.isEmpty() || (taskId == null)) return null;
        Task task = taskRepository.findOne(taskId);
        if ((!userId.equals(task.getUserId())) || (!userRepository.findOne(userId).getRole().equals("admin")))
            return null;
        return taskRepository.remove(taskId);
    }

    public void removeAll(String userId) {
        if (!userRepository.findOne(userId).getRole().equals("admin")) return;
        taskRepository.removeAll();
    }

    public List<Task> findAll(String userId) {
        if (!userRepository.findOne(userId).getRole().equals("admin")) return null;
        return taskRepository.findAll();
    }
}
